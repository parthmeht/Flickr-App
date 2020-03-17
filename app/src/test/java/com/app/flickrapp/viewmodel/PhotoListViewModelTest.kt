package com.app.flickrapp.viewmodel

import com.app.flickrapp.service.model.PhotoItem
import com.app.flickrapp.service.network.FlickrAPI
import com.app.flickrapp.utils.DataGenerator
import com.app.flickrapp.view.ui.MainActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PhotoListViewModelTest {
    @Mock
    internal var mockMainActivity: MainActivity? = null

    @Mock
    internal var flickrAPI: FlickrAPI? = null

    private lateinit var mPhotoListViewModel: PhotoListViewModel

    private lateinit var dummyList: List<PhotoItem>

    @Before
    fun setUp() {
        dummyList = DataGenerator.getDummyGalleryList(85)

        mPhotoListViewModel = PhotoListViewModel()

        mPhotoListViewModel.repositories.value = dummyList
    }

    @Test
    fun test_search_photos(){
        mPhotoListViewModel.searchPhotos("Nature",1)
    }
}