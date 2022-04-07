package com.drivertest.donatenowapp

import androidx.room.TypeConverter
import com.drivertest.donatenowapp.Model.User

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

/**
 * converts List to and from String
 */
class GenreConverters {

    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<User> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<User>>() {

        }.type

        return gson.fromJson<List<User>>(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<User>): String {
        return gson.toJson(someObjects)
    }
}