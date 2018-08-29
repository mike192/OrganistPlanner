package pl.mosenko.songplanner.utils

import java.util.Date

import androidx.room.TypeConverter

object DateTypeConverter {

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date? {
        return if (millisSinceEpoch == null) {
            null
        } else Date(millisSinceEpoch)

    }
}
