package com.exercise.weather.paging

import androidx.paging.PagingSource
import com.exercise.weather.model.Lists
import com.exercise.weather.network.ResponseApi
import com.exercise.weather.utils.ApiConstants
import com.exercise.weather.utils.SystemUtils
import retrofit2.HttpException
import java.io.IOException

class PagingDataSource(private val systemUtils: SystemUtils, private val eventApi: ResponseApi) :
    PagingSource<Int, Lists>() {

    var cityName:String? =null
    lateinit var setAuthorName:String
    /**
     * calls api if there is any error getting results then return the [LoadResult.Error]
     * for successful response return the results using [LoadResult.Page] for some reason if the results
     * are empty from service like in case of no more data from api then we can pass [null] to
     * send signal that source has reached the end of list
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Lists> {
        //for first case it will be null, then we can pass some default value, in our case it's 1
        if (systemUtils.isNetworkConnected()) {
            val page = params.key ?: ApiConstants.DEFAULT_PAGE_INDEX
            return try {
                val response =
                    eventApi.getRequestPagingDataAsync(page, params.loadSize, cityName!!).await()
                if (response.isSuccessful) {
                    response.body()?.list
                    LoadResult.Page(
                        response.body()?.list!!,
                        prevKey = if (page == ApiConstants.DEFAULT_PAGE_INDEX) null else page - 1,
                        nextKey = if (response.body()?.list.isNullOrEmpty()) null else page + 1
                    )
                }else LoadResult.Error(Exception(response.errorBody()?.string()))
            } catch (exception: IOException) {
                return LoadResult.Error(exception)
            } catch (exception: HttpException) {
                return LoadResult.Error(exception)
            }
        }else return LoadResult.Error(Exception(ApiConstants.NO_INTERNET))
    }


}