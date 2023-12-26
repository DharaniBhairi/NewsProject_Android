package com.example.newsproject.topicSelection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsproject.databinding.TopicSingleItemBinding
import com.example.newsproject.topicSelection.model.MainResponse

class TopicAdapter (private var list : ArrayList<MainResponse> = ArrayList(), private var mainItemClickListeners:TopicClickListener): RecyclerView.Adapter<TopicAdapter.ViewHolder>() {

    class ViewHolder(var binding: TopicSingleItemBinding) : RecyclerView.ViewHolder(binding.root)

    interface TopicClickListener {
        fun topicItemClick(cardView: CardView, position: Int)
        fun itemClick(cardView: CardView,position: Int,list: ArrayList<MainResponse>)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TopicSingleItemBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {

            tvCategoryName.text = list[position].title
            ivCategoryImage.setImageResource(list[position].image)

            holder.binding.cardView.setOnClickListener {
                mainItemClickListeners.topicItemClick(cardView,position)
            }
            mainItemClickListeners.itemClick(cardView,position,list)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

