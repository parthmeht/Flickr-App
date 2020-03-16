package com.app.flickrapp.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.app.flickrapp.BaseViewModel
import com.app.flickrapp.service.model.PhotoItem
import com.app.flickrapp.utils.FlickrUtils

class PhotoViewModel: BaseViewModel() {
    private val photoId = MutableLiveData<String>()
    private val photoOwner = MutableLiveData<String>()
    private val photoSecret = MutableLiveData<String>()
    private val photoServer = MutableLiveData<String>()
    private val photoFarm = MutableLiveData<Int>()
    private val photoTitle = MutableLiveData<String>()
    private val photoURL = MutableLiveData<String>()
    private val photoWidth = MutableLiveData<String>()
    private val photoHeight = MutableLiveData<String>()

    fun bind(photoItem: PhotoItem){
        photoId.value = photoItem.id
        photoOwner.value = photoItem.owner
        photoSecret.value = photoItem.secret
        photoServer.value = photoItem.server
        photoFarm.value = photoItem.farm
        photoTitle.value = photoItem.title
        photoWidth.value = photoItem.width_n
        photoHeight.value = photoItem.height_n
        photoURL.value = photoItem.url_n ?: FlickrUtils.getFlickrImageLink(photoItem.id, photoItem.secret, photoItem.server, photoItem.farm, FlickrUtils.SMALL_360)
    }

    fun getPhotoId():MutableLiveData<String>{
        return photoId
    }

    fun getPhotoOwner():MutableLiveData<String>{
        return photoOwner
    }

    fun getPhotoSecret():MutableLiveData<String>{
        return photoSecret
    }

    fun getPhotoServer():MutableLiveData<String>{
        return photoServer
    }

    fun getPhotoFarm():MutableLiveData<Int>{
        return photoFarm
    }

    fun getPhotoTitle():MutableLiveData<String>{
        return photoTitle
    }

    fun getPhotoURL():MutableLiveData<String>{
        return photoURL
    }

    fun getPhotoWidth():MutableLiveData<String>{
        return photoWidth
    }

    fun getPhotoHeight():MutableLiveData<String>{
        return photoHeight
    }

}