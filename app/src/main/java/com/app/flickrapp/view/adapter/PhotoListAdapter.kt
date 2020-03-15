package com.app.flickrapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.flickrapp.R
import com.app.flickrapp.databinding.ItemPhotoBinding
import com.app.flickrapp.service.model.PhotoItem
import com.app.flickrapp.viewmodel.PhotoViewModel

class PhotoListAdapter: RecyclerView.Adapter<PhotoListAdapter.ViewHolder>() {
    private lateinit var photoList:List<PhotoItem>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPhotoBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_photo, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photoList[position])
    }

    override fun getItemCount(): Int {
        return if(::photoList.isInitialized) photoList.size else 0
    }

    fun updatePostList(postList:List<PhotoItem>){
        this.photoList = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemPhotoBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = PhotoViewModel()

        fun bind(photoItem: PhotoItem){
            viewModel.bind(photoItem)
            binding.viewModel = viewModel
        }
    }
}