package com.production.sidorov.ivan.notekotlinmvp.room

import android.arch.persistence.room.TypeConverter
import java.util.*

class DateConverter{
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return when (timestamp) {
            null -> null
            else -> Date(timestamp)
        }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}