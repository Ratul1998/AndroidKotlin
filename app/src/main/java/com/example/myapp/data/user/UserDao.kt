package com.example.myapp.data.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table WHERE email=:email")
    suspend fun getUserByEmail(email : String) : List<User>

    @Query("SELECT * FROM user_table WHERE id=:id")
    suspend fun getUserById(id : Int) : List<User>

}