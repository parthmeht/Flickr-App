package com.app.flickrapp.view.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.app.flickrapp.R
import com.app.flickrapp.databinding.ActivityMainBinding
import com.app.flickrapp.service.model.FlickrSearchResponse
import com.app.flickrapp.service.model.PhotoItem
import com.app.flickrapp.utils.Constants
import com.app.flickrapp.view.adapter.PhotoListAdapter
import com.app.flickrapp.viewmodel.PhotoListViewModel
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() , PhotoListAdapter.OnItemClickListener{

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PhotoListViewModel
    private val photoListAdapter = PhotoListAdapter(arrayListOf(), this)
    var photoItemList = MutableLiveData<List<PhotoItem>>()
    var flickrSearchResponse = MutableLiveData<FlickrSearchResponse>()
    var inputText = MutableLiveData<String>()
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val gridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.layoutManager = gridLayoutManager
        binding.recyclerView.adapter = this.photoListAdapter
        viewModel = ViewModelProviders.of(this).get(PhotoListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        viewModel.photoListAdapter = this.photoListAdapter

        viewModel.flickrSearchResponse.observe(this, Observer { it?.let {
            flickrSearchResponse.value = it
            binding.pageTextView.text = "Page "+flickrSearchResponse.value!!.page.toString()
            if(flickrSearchResponse.value!!.page==1)
                binding.previousImageButton.isClickable = false
            if(flickrSearchResponse.value!!.page == Integer.parseInt(flickrSearchResponse.value!!.total))
                binding.nextImageButton.isClickable = false
        } })

        viewModel.repositories.observe(this, Observer { it?.let{
            photoListAdapter.updatePostList(it)
            photoItemList.value = it
        } })

        viewModel.loadingVisibility.value = View.GONE

        binding.previousImageButton.setOnClickListener{
            if (!binding.nextImageButton.isClickable)
                binding.nextImageButton.isClickable = true
            if (flickrSearchResponse.value!=null && flickrSearchResponse.value!!.page>1){
                viewModel.searchPhotos(inputText.value.toString(), flickrSearchResponse.value!!.page - 1)
            }else
                showError(R.string.previous_clicked)
        }

        binding.nextImageButton.setOnClickListener{
            if (!binding.previousImageButton.isClickable)
                binding.previousImageButton.isClickable = true
            if (flickrSearchResponse.value!=null && flickrSearchResponse.value!!.page < Integer.parseInt(
                    flickrSearchResponse.value!!.total)){
                viewModel.searchPhotos(inputText.value.toString(), flickrSearchResponse.value!!.page + 1)
            }else
                showError(R.string.next_clicked)
        }

        binding.searchButton.setOnClickListener {
            inputText.value = binding.inputEditText.text.toString()
            if (inputText.value != "")
                viewModel.searchPhotos(inputText.value!!,1)
            else
                showError(R.string.blank_input)
        }

        binding.viewModel = viewModel
        binding.executePendingBindings()
    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }

    override fun onItemClick(position: Int) {
        Log.d("Click Main", position.toString())
        val intent = Intent (this@MainActivity, ImageViewActivity::class.java)
        val bundle = Bundle()
        intent.putExtra(Constants.PHOTO_ITEM, photoItemList.value!![position])
        startActivity(intent)
    }
}
