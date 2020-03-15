package com.app.flickrapp.service.model

data class PhotoItem(
    var id: String,
    var owner: String,
    var secret: String,
    var server: String,
    var farm: Int,
    var title: String?,
    var ispublic: Short,
    var url_n: String?,
    var width_n: String?,
    var height_n: String?)
