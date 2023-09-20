package com.example.api2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.api2.Utils.getDateFromTimestampWithFormat
import com.example.api2.databinding.SearchItemBinding

class BookmarkAdapter(var mContext: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = mutableListof<SearchItemModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        Glide.with(mContext)
            .load(items[position].url)
            .into(holder as ItemViewHolder).iv_thum_image
        holder.tv_title.text = items[position].tilte
        holder.iv_like.visibility = View.GONE
        holder.tv_datetime.text =
            getDateFromTimestampWithFormat(
                items[position].dataTime,
            "yyy-MM-dd'T'HH:mm:ss.SSS+09:00",
            "yyyy-MM-dd HH:mm:ss"
        )
    }

    override fun getItemCount():Int{
        return items.size
    }

    inner class ItemViewHolder(binding: SearchItemBinding): RecyclerView.ViewHolder(binding.root){

        var iv_thum_image: ImageView = binding.ivThumImage
        var iv_like: ImageView = binding.ivLike
        var tv_title: TextView = binding.tvTitle
        var tv_datetime: TextView = binding.tvDatetime
        var cl_item: ConstraintLayout = binding.clThumbItem

        init {
            iv_like.visibility = View.GONE

            cl_item.setOnClickListener{
                val position = adapterPosition
                (mContext as MainActivity).removeLikedItem(items[position])
                if (position != RecyclerView.NO_POSITION){
                    items.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
        }


    }
}