package com.exercise.weather.utils

object ApiConstants {



    const val INTENT_DATA: String = "INTENT_DATA"
    const val CITY_NAME: String = "INTENT_CITY_NAME"
    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    const val CONTENT_TYPE = "Content-Type"
    const val APPLICATION_JSON = "application/json"
    const val x2 = "2x"
    const val x4 = "4x"
    const val API_KEY = "REPLACE WITH VALID API KEY"

    //errors
    const val NO_INTERNET = "No internet connection"
    const val UN_DEFINED_ERROR = "No result found"

    const val DATE_OUTPUT = "EEEE, MMM h a"
    const val DATE_INPUT = "yyyy-MM-dd HH:mm:ss"
    //paging
    const val DEFAULT_PAGE_INDEX = 1
    const val DEFAULT_PAGE_SIZE = 5
    const val MAX_PAGE_SIZE = 1000

    // view holder
    const val BADGE_VIEW = 0
    const val TAG_VIEW = 1

}
