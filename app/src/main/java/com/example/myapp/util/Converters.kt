package com.example.myapp.util

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun listToJson(value: Array<Int>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String): Array<Int> = Gson().fromJson(value, Array<Int>::class.java)

}