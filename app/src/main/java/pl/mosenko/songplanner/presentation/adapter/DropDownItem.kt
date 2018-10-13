package pl.mosenko.songplanner.presentation.adapter

import java.io.Serializable

data class DropDownItem<out KEY, out VALUE>(open val key: KEY, val value: VALUE) : Serializable {
    override fun toString(): String = "$value"
}
