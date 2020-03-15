package com.app.flickrapp

import androidx.lifecycle.ViewModel
import com.app.flickrapp.injection.component.DaggerViewModelInjector
import com.app.flickrapp.injection.component.ViewModelInjector
import com.app.flickrapp.injection.module.NetworkModule
import com.app.flickrapp.viewmodel.PhotoListViewModel

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is PhotoListViewModel -> injector.inject(this)
        }
    }}