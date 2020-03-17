package com.app.flickrapp.service.model

data class ResponsePhotoItemHolder(
    val stat: String,
    val photos: FlickrSearchResponse
)