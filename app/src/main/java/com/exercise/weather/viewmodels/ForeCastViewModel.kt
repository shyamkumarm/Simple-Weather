package com.exercise.weather.viewmodels

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.exercise.weather.model.Lists
import com.exercise.weather.network.response.Resource
import com.exercise.weather.paging.PagingRepo


class ForeCastViewModel (private val pagingRepo: PagingRepo) : ViewModel() {


    var createdTodo = MutableLiveData<Resource<Lists>>()




    fun getPagingTodo(city:String,pageSize: Int): LiveData<PagingData<Lists>> {
        return pagingRepo.getPagingTagTodoReq(city,pageSize)
            .map { it -> it.map { it.apply { it.getDateFormat() } } }
            .cachedIn(viewModelScope)
    }


}