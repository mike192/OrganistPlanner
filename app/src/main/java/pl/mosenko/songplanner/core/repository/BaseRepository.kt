package pl.mosenko.songplanner.core.repository

import io.reactivex.Single
import org.koin.standalone.KoinComponent

interface BaseRepository<T> : KoinComponent {
    fun insert(entity: T): Single<Long>

    fun delete(entity: T): Single<Int>
}