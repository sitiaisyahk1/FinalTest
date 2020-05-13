package com.sitiaisyah.idn.finaltest.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sitiaisyah.idn.finaltest.room.Comment

@Dao
interface CommentDao {
    @Query("SELECT * FROM comment_table ORDER BY comment ASC")
    fun getAlphaWords() : LiveData<List<Comment>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(comment: Comment)

    @Query("DELETE FROM comment_table")
    fun deleteAll()

}
