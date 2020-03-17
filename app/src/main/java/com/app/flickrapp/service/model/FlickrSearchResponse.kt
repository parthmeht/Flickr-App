package com.app.flickrapp.service.model

data class FlickrSearchResponse(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val total: String,
    val photo: List<PhotoItem>
)

