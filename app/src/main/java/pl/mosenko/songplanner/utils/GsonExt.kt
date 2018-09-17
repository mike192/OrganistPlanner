package pl.mosenko.songplanner.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader


inline fun <reified T> Gson.fromJsonFile(appContext: Context, fileName: String): T {
    var jsonReader: JsonReader? = null
    return try {
        val inputStream = appContext.assets.open(fileName)
        jsonReader = JsonReader(inputStream.reader())
        val typeToken: TypeToken<T> = object : TypeToken<T>() {}
        fromJson(jsonReader, typeToken.type)
    } finally {
        jsonReader?.close()
    }
}