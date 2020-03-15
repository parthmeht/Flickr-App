package com.app.flickrapp.service.network

import com.app.flickrapp.service.model.ResponsePhotoItemHolder
import com.app.flickrapp.utils.FlickrUtils
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//www.reddit.com/top.json?limit=10

interface FlickrAPI {
    /*@GET("/services/rest/")
    fun getSearchResults(
        @Query("method") method: String,
        @Query("api_key") api_key: String,
        @Query("text") text: String,
        @Query("format") format: String,
        @Query("nojsoncallback") noJsonCallBack: Number,
        @Query("page") page: Number
    ): Observable<ResponsePhotoItemHolder>*/

    @GET("rest?format=json&nojsoncallback=1&method=" + FlickrUtils.METHOD_SEARCH + "&extras=url_" + FlickrUtils.SMALL_360 +
            "&safe_search=1")
    fun getSearchResults(@Query("api_key") key: String,
                         @Query("text") query: String,
                         @Query("page") page: Int,
                         @Query("per_page") perPage: Int): Observable<ResponsePhotoItemHolder>
}