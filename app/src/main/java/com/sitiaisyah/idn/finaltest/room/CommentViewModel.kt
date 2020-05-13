package com.sitiaisyah.idn.finaltest.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sitiaisyah.idn.finaltest.room.Comment
import com.sitiaisyah.idn.finaltest.room.CommentRepo
import com.sitiaisyah.idn.finaltest.room.CommentRoomDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CommentViewModel (commentApp: Application) : AndroidViewModel(commentApp){
    private val repository : CommentRepo

    val allComments: LiveData<List<Comment>>

    init {
        val commentsDao = CommentRoomDb.getDb(commentApp, viewModelScope).commentDao()
        repository = CommentRepo(commentsDao)
        allComments = repository.allComment
    }

    fun insert(comment: Comment) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(comment)
    }
}