package pl.mosenko.songplanner

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.android.startKoin
import pl.mosenko.songplanner.core.di.buildBaseModule
import timber.log.Timber

@Suppress("unused")
class BaseApplication : Application() {

    private val baseModule = buildBaseModule()

    override fun onCreate() {
        super.onCreate()
        initializeTimber()
        startKoin(this, listOf(baseModule))
        AndroidThreeTen.init(this)
    }

    private fun initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}