package com.uoa.telmaticsapp.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.uoa.telmaticsapp.data.model.Source

class Converters {
    @TypeConverter
    fun fromSource(source: Source):String{
           return source.name
    }
    @TypeConverter
    fun toSource(name:String):Source{
        return Source(name,name)
    }

    @TypeConverter
    fun fromGroupTaskMemberList(value: List<Float>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Float>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toGroupTaskMemberList(value: String): List<Float> {
        val gson = Gson()
        val type = object : TypeToken<List<Float>>() {}.type
        return gson.fromJson(value, type)
    }
}


