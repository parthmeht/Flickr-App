package com.app.flickrapp.injection.component

import com.app.flickrapp.injection.module.NetworkModule
import com.app.flickrapp.viewmodel.PhotoListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param photoListViewModel PostListViewModel in which to inject the dependencies
     */
    fun inject(photoListViewModel: PhotoListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}