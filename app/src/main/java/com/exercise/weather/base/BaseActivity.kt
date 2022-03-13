package com.exercise.weather.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.exercise.weather.viewmodels.ForeCastViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseActivity : AppCompatActivity() {

    protected val viewModel: ForeCastViewModel by  viewModel()
    abstract fun getContentLayout(): View
    abstract fun onCreate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         onCreate()
        setContentView(getContentLayout())
    }



    protected fun showToast(msg: String?,duration:Int =  Toast.LENGTH_SHORT) {
        Toast.makeText(baseContext, msg, duration).show()
    }

    protected fun clearFocusKeyboard(){
        val input: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        input.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}