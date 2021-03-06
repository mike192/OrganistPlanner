package pl.mosenko.songplanner.core.db

import androidx.room.Delete
import androidx.room.Insert

interface BaseDao<T> {
    @Insert
    fun insert(entity: T): Long

    @Delete
    fun delete(entity: T): Int
}