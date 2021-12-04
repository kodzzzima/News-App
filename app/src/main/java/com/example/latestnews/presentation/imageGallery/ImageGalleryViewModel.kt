package com.example.latestnews.presentation.imageGallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.latestnews.data.NewsRepository
import com.example.latestnews.util.DetailedNewsResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageGalleryViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val imageArray: Array<String> =
        savedStateHandle.get<Array<String>>(URL_IMAGES_ARRAY) ?: emptyArray()

    private val _imagesLiveData =
        MutableLiveData<Array<String>>()
    val imagesLiveData: LiveData<Array<String>>
        get() = _imagesLiveData

    init {
        getImages()
    }

    private fun getImages() {
        _imagesLiveData.postValue(imageArray)
    }

    companion object {
        const val URL_IMAGES_ARRAY = "URL_IMAGES_ARRAY"
    }
}