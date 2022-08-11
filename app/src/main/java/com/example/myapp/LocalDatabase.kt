package com.example.myapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapp.data.user.User
import com.example.myapp.data.user.UserDao

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract  fun userDao() : UserDao

    companion object{
        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getDatabase(context: Context) : LocalDatabase {
            val temInstance = INSTANCE
            if(temInstance!=null){
                return temInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    "local_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}