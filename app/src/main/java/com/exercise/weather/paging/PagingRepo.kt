package com.exercise.weather.paging

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.exercise.weather.model.Lists
import org.koin.java.KoinJavaComponent.getKoin

class PagingRepo {

    private fun getDefaultPageConfig(pageSize:Int): PagingConfig {
        return PagingConfig(pageSize = pageSize, enablePlaceholders = false,prefetchDistance = 5,
            initialLoadSize = pageSize*3)
    }

    private fun getPagingDataSrc(): PagingDataSource = getKoin().get(PagingDataSource::class)




    fun getPagingTagTodoReq(city:String,pageSize: Int): LiveData<PagingData<Lists>> {
        return Pager(
            config = getDefaultPageConfig(pageSize),
            pagingSourceFactory = { getPagingDataSrc().apply {
                cityName = city} }
        ).liveData
    }

}