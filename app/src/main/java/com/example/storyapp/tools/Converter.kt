package com.example.storyapp.tools

import androidx.room.TypeConverter

class Converter {
    @TypeConverter
    fun fromLat(value: Any?): String {
        return value.toString()
    }

    @TypeConverter
    fun toLat(value: String?): Any? {
        return value
    }
}