package com.sitiaisyah.idn.finaltest.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Comment::class], version = 1)
abstract class CommentRoomDb : RoomDatabase() {
    abstract fun commentDao() : CommentDao

    companion object{

        @Volatile
        private var INSTANCE: CommentRoomDb? = null

        fun getDb(context: Context, scope: CoroutineScope): CommentRoomDb {
            return INSTANCE
                ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext, CommentRoomDb::class.java, "comment_db"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(
                        CommentDbCallback(
                            scope
                        )
                    ).build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class CommentDbCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO){
                    accessDatabase(database.commentDao())
                }
            }
        }

        private fun accessDatabase(commentDao: CommentDao) {
            commentDao.deleteAll()

            var comment =
                Comment("Hot Florida New Home Deals Dont Miss A New Listing Again")
            commentDao.insert(comment)

            comment =
                Comment("Design House Stockholm is a Publishing House for Scandinavian Design.")
            commentDao.insert(comment)

            comment =
                Comment("Design your dream home effortlessly and have fun. An advanced and easy to use 2D 3D home design tool Planner5D.")
            commentDao.insert(comment)
        }
    }
}