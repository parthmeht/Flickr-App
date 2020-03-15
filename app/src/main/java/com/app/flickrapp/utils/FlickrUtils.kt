package com.app.flickrapp.utils

object FlickrUtils {

    const val DEFAULT_PAGE_SIZE = 100
    const val DEFAULT_QUERY = "nature"
    const val API_KEY = "5ba3fa8dff6e60ab9c0e513e2768b073"

    const val METHOD_SEARCH_RECENT = "flickr.photos.getRecent"
    const val METHOD_SEARCH = "flickr.photos.search"

    const val SMALL_SQUARE = "s"
    const val LARGE_SQUARE = "q"
    const val THUMBNAIL = "t"
    const val SMALL_240 = "m"
    const val SMALL_360 = "n"
    const val MEDIUM_500 = "-"
    const val MEDIUM_640 = "z"
    const val MEDIUM_800 = "c"
    const val MEDIUM_1024 = "b"
    const val LARGE_1600 = "h"
    const val LARGE_2048 = "k"

    fun getFlickrImageLink(id: String, secret: String, serverId: String, farmId: Int, size: String): String {
        return "https://farm$farmId.staticflickr.com/$serverId/${id}_${secret}_$size.jpg"
    }

}