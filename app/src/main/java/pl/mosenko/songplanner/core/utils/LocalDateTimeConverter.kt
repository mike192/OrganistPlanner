package pl.mosenko.songplanner.core.utils

import androidx.room.TypeConverter
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId

class LocalDateTimeConverter {

    @TypeConverter
    fun fromDate(date: LocalDateTime?): Long? {
        return date?.atZone(ZoneId.systemDefault())?.toInstant()?.toEpochMilli()
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): LocalDateTime? =
        if (millisSinceEpoch == null) {
            null
        } else {
            Instant.ofEpochMilli(millisSinceEpoch).atZone(ZoneId.systemDefault()).toLocalDateTime()
        }
}
