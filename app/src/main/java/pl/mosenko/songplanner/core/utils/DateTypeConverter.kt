package pl.mosenko.songplanner.core.utils

import androidx.room.TypeConverter
import java.util.*

class DateTypeConverter {

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
