package com.exercise.weather.model

import android.os.Parcelable
import android.util.Log
import com.exercise.weather.utils.ApiConstants
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class Lists(

    val clouds: Clouds,
    val dt: Int,
    @SerializedName("dt_txt")
    var date: String,
    val main:  Main,
    val pop: Double,
    val rain:  Rain?,
    val sys:  Sys,
    val visibility: Int,
    val weather:  ArrayList<Weather>,
    val wind:  Wind,
):Parcelable{

    fun getDateFormat()=  ApiConstants.let {

        date =  it.DATE_OUTPUT.designDate().format(it.DATE_INPUT.designDate().parse(date) as Date)
    }


    private fun String.designDate():SimpleDateFormat =  SimpleDateFormat(this, Locale.getDefault())
}