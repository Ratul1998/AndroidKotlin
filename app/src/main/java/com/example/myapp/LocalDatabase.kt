package com.example.myapp

import android.content.Context
import androidx.room.*
import com.example.myapp.data.movies.Movie
import com.example.myapp.data.movies.MovieDao
import com.example.myapp.data.user.User
import com.example.myapp.data.user.UserDao
import com.example.myapp.util.Converters

@Database(entities = [User::class,Movie::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class LocalDatabase : RoomDatabase() {

    abstract  fun userDao() : UserDao

    abstract  fun movieDao() : MovieDao

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