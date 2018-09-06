package pl.mosenko.songplanner.utilities

import androidx.lifecycle.LiveData
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


@Throws(InterruptedException::class)
fun <T> LiveData<T>.getBlockingValue(): T {
    val data = arrayOfNulls<Any>(1)
    val latch = CountDownLatch(1)
    this.observeForever { result ->
        data[0] = result
        latch.countDown()
    }
    latch.await(1500, TimeUnit.MILLISECONDS)
    @Suppress("UNCHECKED_CAST")
    return data[0] as T
}
