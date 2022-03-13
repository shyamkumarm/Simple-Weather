package com.exercise.weather.view


import android.content.Intent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.weather.databinding.ActivityMainBinding
import com.exercise.weather.model.Lists
import com.exercise.weather.base.BaseActivity

import com.exercise.weather.network.response.Resource
import com.exercise.weather.network.response.Status
import com.exercise.weather.utils.ApiConstants
import com.exercise.weather.view.adapter.ViewAdapter
import kotlinx.coroutines.launch


class MainActivity : BaseActivity() {

    private lateinit var viewAdapter: ViewAdapter


    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun getContentLayout(): View = binding.root
    override fun onCreate() {

        initView()
        initObserver()


    }

    private fun initView() {
        setSupportActionBar(binding.toolbar)
        ViewCompat.setTranslationZ(binding.mainContent.progress, 5f)
        binding.mainContent.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
           viewAdapter =  ViewAdapter { _, data ->
               Intent(this@MainActivity,DetailedViewActivity::class.java).run {
                   putExtra(ApiConstants.INTENT_DATA,data)
                   putExtra(ApiConstants.CITY_NAME,binding.mainContent.outlinedTextField.editText?.text.toString())
                   startActivity(this)
               }

           }
            adapter = viewAdapter

        }


        binding.mainContent.outlinedTextField.editText?.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH && v.text.isNotBlank()) {
                clearFocusKeyboard()
                showToast("Searching for ${v.text}")
                getTodos(v.text.toString())
                true
            }else{
                v.error = "Enter the city name"
                false
            }

        }
    }

    private fun getTodos(cityName:String) {
        viewModel.getPagingTodo(cityName, ApiConstants.DEFAULT_PAGE_SIZE).observe(this, {
            lifecycleScope.launch {
                viewAdapter.submitData(it)
            }
        })
    }

    private fun initObserver() {
       initCallback()
    }

    private fun initCallback() {
        viewAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.Error ->
                    loadingStatus(
                        Resource.error(
                            (it.refresh as LoadState.Error).error.message ?: "", null
                        )
                    )
                is LoadState.Loading -> loadingStatus(Resource.loading(null))
                else -> { // success
                    binding.mainContent.progress.visibility = View.GONE
                    binding.mainContent.recyclerView.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun loadingStatus(status: Resource<Lists>) {

        if(Status.LOADING ==status.status) {
            binding.mainContent.progress.visibility = View.VISIBLE
        }
        else {
            //Handle Error
            binding.mainContent.progress.visibility = View.GONE
            showToast(status.message, Toast.LENGTH_LONG)
            binding.mainContent.outlinedTextField.editText?.error = status.message
        }
    }
}


