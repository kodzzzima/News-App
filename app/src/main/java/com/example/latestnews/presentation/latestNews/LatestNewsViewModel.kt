package com.example.latestnews.presentation.latestNews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.latestnews.data.NewsRepository
import com.example.latestnews.util.NewsResult
import com.example.latestnews.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LatestNewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _newsResultLiveData =
        MutableLiveData<NewsResult>(NewsResult.Loading)
    val newsResultLiveData: LiveData<NewsResult>
        get() = _newsResultLiveData

    init {
        getNewsList()
    }

    fun getNewsList() {
        viewModelScope.launch(Dispatchers.IO) {
            _newsResultLiveData.postValue(handleNewsList())
        }
    }

    private suspend fun handleNewsList(): NewsResult {
        return when (val result = newsRepository.searchLastNews()) {
            is Result.Error -> NewsResult.ErrorResult(result.result)
            is Result.Success -> {
                if (result.result.isNullOrEmpty()) NewsResult.EmptyResult
                else NewsResult.SuccessResult(result.result)
            }
        }
    }
}