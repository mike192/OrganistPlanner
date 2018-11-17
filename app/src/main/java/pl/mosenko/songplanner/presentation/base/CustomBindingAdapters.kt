package pl.mosenko.songplanner.presentation.base

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("srcCompat")
fun ImageView.setImageViewDrawable(drawable: Drawable) {
    setImageDrawable(drawable)
}