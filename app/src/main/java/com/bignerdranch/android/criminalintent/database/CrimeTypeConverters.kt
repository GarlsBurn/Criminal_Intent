package com.bignerdranch.android.criminalintent.database

import androidx.room.TypeConverter
import java.util.Date
import java.util.UUID

class CrimeTypeConverters {

    /*@TypeConverter
    fun fromDate(date: Date): Long?{
        return date?.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date?{
        return  millisSinceEpoch?.let{
            Date(it)
        }
    }*/

    @TypeConverter
    fun toUUID(uuid: UUID?): UUID?{
        return UUID.fromString(uuid.toString())
    }

    @TypeConverter
    fun fromUUID(uuid: UUID?): String?{
        return uuid?.toString()
    }
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

}