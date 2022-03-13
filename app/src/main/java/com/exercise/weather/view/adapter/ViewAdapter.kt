package com.exercise.weather.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.exercise.weather.databinding.ItemLayoutBinding
import com.exercise.weather.model.Lists
import com.exercise.weather.utils.ImageLoader


class ViewAdapter(
    val callback:(Int,Lists)->Unit
) : PagingDataAdapter<Lists, ViewAdapter.ViewHolder>(diffCallback) {

    private val TAG = "ViewAdapter"



    fun getLastPosition():Int = itemCount-1
    var getLastClickedPos:Int = 0




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

      return  ViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        Log.d(TAG,item.toString())
        holder.mView.root.setOnClickListener {
            getLastClickedPos = holder.absoluteAdapterPosition
            callback(
                holder.absoluteAdapterPosition,
                getItem(holder.absoluteAdapterPosition)!!
            )
        }

        holder.mView.apply {
                date.text = item?.date
                weatherName.text = item?.weather?.get(0)?.main
                 temp.text = String.format( " %d\u2103",item?.main?.temp?.toInt())
                  ImageLoader.loadImage(item?.weather?.get(0)?.icon,image)

        }


    }

    fun notifyMarkedChanges() {
    notifyItemChanged(getLastClickedPos)
    }

    fun notifyAddedChanges() {
        notifyItemInserted(getLastPosition())

    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Lists>() {
            override fun areItemsTheSame(oldItem: Lists, newItem: Lists): Boolean =
                 oldItem.dt == newItem.dt && oldItem.date == newItem.date

            override fun areContentsTheSame(oldItem: Lists, newItem: Lists): Boolean =
                oldItem == newItem
        }
    }

    inner class ViewHolder(val mView: ItemLayoutBinding) : RecyclerView.ViewHolder(mView.root)


}



