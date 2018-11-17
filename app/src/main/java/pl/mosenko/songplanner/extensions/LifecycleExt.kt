package pl.mosenko.songplanner.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T : Any> LifecycleOwner.observe(liveData: LiveData<T>, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))