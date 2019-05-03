package pl.mosenko.songplanner.core.adapter

import java.io.Serializable

data class DropDownItem<out KEY, out VALUE>(
    val key: KEY,
    val value: VALUE,
    val originalObject: Any? = null
) : Serializable {
    override fun toString(): String = "$value"
}
