package com.app.flickrapp.service.model

data class FlickrSearchReponse(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val total: String,
    val photo: List<PhotoItem>
)

