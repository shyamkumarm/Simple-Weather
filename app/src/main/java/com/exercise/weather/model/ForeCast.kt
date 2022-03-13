package com.exercise.weather.model

import com.google.gson.annotations.SerializedName

data class ForeCast(
    val city: City,
    val cnt: Int,
    val cod: String,
    @SerializedName("list") val list: List<Lists>,
    val message: Int
)