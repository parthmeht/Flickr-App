package com.app.flickrapp.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.app.flickrapp.R
import com.app.flickrapp.databinding.ActivityImageViewBinding
import com.app.flickrapp.service.model.PhotoItem
import com.app.flickrapp.utils.Constants
import com.app.flickrapp.viewmodel.PhotoViewModel

class ImageViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageViewBinding
    private lateinit var viewModel: PhotoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view)
        val bundle: Bundle? = intent.extras
        val photoItem = bundle?.getSerializable(Constants.PHOTO_ITEM) as PhotoItem?
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_view)
        viewModel = ViewModelProviders.of(this).get(PhotoViewModel::class.java)
        if (photoItem != null) {
            viewModel.bind(photoItem)
        }
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }
}
