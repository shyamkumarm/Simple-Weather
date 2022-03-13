package com.exercise.weather.view

import com.exercise.weather.databinding.ActivityDetailedViewBinding
import com.exercise.weather.base.BaseActivity
import com.exercise.weather.model.Lists
import com.exercise.weather.utils.ApiConstants
import com.exercise.weather.utils.ImageLoader

class DetailedViewActivity : BaseActivity() {


    private val detailedView  by lazy {
        ActivityDetailedViewBinding.inflate(layoutInflater)
    }

    private val dataObject by lazy {    intent.getParcelableExtra<Lists>(ApiConstants.INTENT_DATA)}
    override fun getContentLayout() = detailedView.root
    override fun onCreate() {
        initView()
        fetchValues()
    }

    private fun fetchValues() {
        dataObject?.run {
            detailedView.let {
                it.dateText.text = date
                it.temp.text =  String.format( "Temperature\n%d\u2103", main.temp.toInt())
                it.feelLike.text =   String.format( "Feels like\n%s", main.feels_like.toString())
                it.weatherName.text =  String.format( "Weather:\n%s", weather[0].main)
                it.weatherDesc.text =   String.format( "Climate:\n%s", weather[0].description)
                ImageLoader.loadImage(weather[0].icon,it.appCompatImageView,ApiConstants.x4)

            }

        }

    }

    private fun initView() {
        setSupportActionBar(detailedView.toolbar.apply { title = intent.getStringExtra(ApiConstants.CITY_NAME) })
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}