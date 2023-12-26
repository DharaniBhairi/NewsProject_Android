package com.example.newsproject.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsproject.R
import com.example.newsproject.dataBase.DataBaseResponse
import com.example.newsproject.databinding.HomeSingleItemBinding
import com.example.newsproject.home.model.NewsResponse
import com.example.newsproject.newsDetails.NewsDetailsActivity
import com.example.newsproject.utils.MyApiResponse

class HomeAdapter(var context: Context, private var list: MyApiResponse<NewsResponse>, private val saveClickListeners: SaveItemClickListener): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    class ViewHolder(var binding: HomeSingleItemBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var saveDataList : DataBaseResponse

    interface SaveItemClickListener {
        fun saveItemClick(position: Int, saveDataList: DataBaseResponse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HomeSingleItemBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {

            tvSingleNewsTitle.text = list.data?.results?.get(position)?.title

            if (list.data?.results?.get(position)?.image_url?.length == null)
            {
                Glide.with(context).load(R.drawable.default_image).into(holder.binding.ivSingleNews)
            }
            else
            {
                Glide.with(context).load(list.data?.results?.get(position)?.image_url).into(holder.binding.ivSingleNews)
            }

            holder.binding.btnSave.setOnClickListener{
                saveDataList=
                    DataBaseResponse(list.data?.results?.get(position)?.image_url.toString(),list.data?.results?.get(position)?.title.toString())
                saveClickListeners.saveItemClick(position,saveDataList)
            }

            holder.binding.cardView.setOnClickListener{
                val intent = Intent(holder.binding.cardView.context, NewsDetailsActivity::class.java)
                intent.putExtra("image_data", list.data?.results?.get(position)?.image_url)
                intent.putExtra("description_data", list.data?.results?.get(position)?.description)
                holder.binding.cardView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.data?.results?.size!!
    }

}