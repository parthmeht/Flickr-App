package com.app.flickrapp.view.adapter

import com.app.flickrapp.viewmodel.PhotoViewModel

interface ClickListener {
    fun onClick(photoViewModel: PhotoViewModel)
}