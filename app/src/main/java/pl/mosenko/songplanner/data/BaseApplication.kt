package pl.mosenko.songplanner.data

import android.app.Application
import org.koin.android.ext.android.startKoin
import pl.mosenko.songplanner.BuildConfig
import pl.mosenko.songplanner.di.buildBaseModule
import timber.log.Timber

class BaseApplication : Application() {

    private val baseModule = buildBaseModule()

    override fun onCreate() {
        super.onCreate()
        initializeTimber()
        startKoin(this, listOf(baseModule))
    }

    private fun initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}