package com.exercise.weather.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.exercise.weather.R
import java.util.*

object ImageLoader {

     fun getRandomId():String = "temp=${UUID.randomUUID()}"

    fun loadImage(
        imageUrl: String?,
        imageViewToLoad: ImageView,
        res: String = ApiConstants.x2
    ) {
        Glide.with(imageViewToLoad.context).load("https://openweathermap.org/img/wn/$imageUrl@$res.png").apply {
            transform(CenterCrop())
                .placeholder(R.drawable.ic_baseline_image_24)
                .transition(DrawableTransitionOptions.withCrossFade(DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()))

        }.into(imageViewToLoad)

    }

   //


}
