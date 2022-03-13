package com.exercise.weather.network

import com.exercise.weather.model.ForeCast
import com.exercise.weather.utils.ApiConstants
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*


interface ResponseApi {

    // not for paging
    @GET("forecast?")
    fun getRequestDataAsync(@Query("q") location: String,
                            @Query("units") units: String ="metric",@Query("appid") author: String = ApiConstants.API_KEY): Deferred<Response<ForeCast>>







     // paging
    @GET("forecast?")
    fun getRequestPagingDataAsync(@Query("_page") pageSize: Int,
                                  @Query("_limit") portalId: Int,@Query("q") location: String,
                                  @Query("units") units: String ="metric",@Query("appid") author: String = ApiConstants.API_KEY): Deferred<Response<ForeCast>>
}