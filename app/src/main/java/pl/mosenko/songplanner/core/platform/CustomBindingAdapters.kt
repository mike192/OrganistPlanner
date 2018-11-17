package pl.mosenko.songplanner.core.platform

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("srcCompat")
fun ImageView.setImageViewDrawable(drawable: Drawable) {
    setImageDrawable(drawable)
}