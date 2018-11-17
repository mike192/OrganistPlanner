package pl.mosenko.songplanner.extensions

import android.content.Context
import androidx.annotation.RawRes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader


inline fun <reified T> Gson.fromJsonFile(appContext: Context, @RawRes fileId: Int): T {
    var jsonReader: JsonReader? = null
    return try {
        val inputStream = appContext.resources.openRawResource(fileId)
        jsonReader = JsonReader(inputStream.reader())
        val typeToken: TypeToken<T> = object : TypeToken<T>() {}
        fromJson(jsonReader, typeToken.type)
    } finally {
        jsonReader?.close()
    }
}