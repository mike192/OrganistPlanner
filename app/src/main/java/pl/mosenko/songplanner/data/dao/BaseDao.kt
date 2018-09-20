package pl.mosenko.songplanner.data.dao

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert

interface BaseDao<T> {
    @Insert
    fun insert(entity: T): Long

    @Delete
    fun delete(entity: T): Int
}