package com.app.flickrapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.flickrapp.R
import com.app.flickrapp.databinding.ItemPhotoBinding
import com.app.flickrapp.service.model.PhotoItem
import com.app.flickrapp.viewmodel.PhotoViewModel

class PhotoListAdapter(private var photoList: List<PhotoItem>,
                       private var listener: OnItemClickListener): RecyclerView.Adapter<PhotoListAdapter.ViewHolder>() {
    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding: ItemPhotoBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_photo, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photoList[position], position, listener)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    fun updatePostList(postList:List<PhotoItem>){
        this.photoList = postList
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class ViewHolder(private val binding: ItemPhotoBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = PhotoViewModel()
        fun bind(photoItem: PhotoItem, position: Int, listener: OnItemClickListener?){
            viewModel.bind(photoItem)
            binding.viewModel = viewModel
            binding.position = position
            if (listener != null) {
                binding.root.setOnClickListener { _ -> listener.onItemClick(layoutPosition) }
            }
            binding.executePendingBindings()
        }
    }
}