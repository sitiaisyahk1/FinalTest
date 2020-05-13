package com.sitiaisyah.idn.finaltest.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class CommentRepo(private val commentDao : CommentDao) {
    val allComment : LiveData<List<Comment>> = commentDao.getAlphaWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(comment: Comment){
        commentDao.insert(comment)
    }
}