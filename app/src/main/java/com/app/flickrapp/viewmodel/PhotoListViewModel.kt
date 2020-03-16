package com.app.flickrapp.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.app.flickrapp.BaseViewModel
import com.app.flickrapp.R
import com.app.flickrapp.service.model.PhotoItem
import com.app.flickrapp.service.model.ResponsePhotoItemHolder
import com.app.flickrapp.service.network.FlickrAPI
import com.app.flickrapp.utils.FlickrUtils.API_KEY
import com.app.flickrapp.view.adapter.PhotoListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PhotoListViewModel: BaseViewModel() {
    @Inject
    lateinit var flickrAPI: FlickrAPI
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    var repositories = MutableLiveData<List<PhotoItem>>()
    val selected:MutableLiveData<PhotoViewModel> = MutableLiveData()
    val errorClickListener = View.OnClickListener {  }
    private lateinit var subscription: Disposable
    lateinit var  photoListAdapter: PhotoListAdapter

    fun searchPhotos(text: String, page: Int){
        subscription = flickrAPI.getSearchResults(API_KEY,text, page, 100)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveSearchPhotosStart() }
            .doOnTerminate { onRetrieveSearchPhotosFinish() }
            .subscribe(
                { result -> onRetrieveSearchPhotosSuccess(result) },
                { onRetrieveSearchPhotosError() }
            )
    }

    private fun onRetrieveSearchPhotosStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveSearchPhotosFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveSearchPhotosSuccess(response: ResponsePhotoItemHolder){
        repositories.value = response.photos.photo
    }

    private fun onRetrieveSearchPhotosError(){
        errorMessage.value = R.string.photo_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}