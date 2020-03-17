package com.app.flickrapp.service.model

data class FlickrSearchResponse(
    var page: Int,
    var pages: Int,
    var perpage: Int,
    var total: String,
    var photo: List<PhotoItem>
)

