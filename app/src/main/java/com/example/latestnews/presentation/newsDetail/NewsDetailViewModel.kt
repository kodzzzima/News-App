package com.example.latestnews.presentation.newsDetail

import androidx.lifecycle.*
import com.example.latestnews.data.NewsRepository
import com.example.latestnews.util.DetailedNewsResult
import com.example.latestnews.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val newsId: String = savedStateHandle.get<String>(News_ID).orEmpty()
    private val newsTitle: String = savedStateHandle.get<String>(News_TITLE).orEmpty()

    private val _detailedNewsResultLiveData =
        MutableLiveData<DetailedNewsResult>(DetailedNewsResult.Loading(title = newsTitle))
    val detailedNewsResultLiveData: LiveData<DetailedNewsResult>
        get() = _detailedNewsResultLiveData

    init {
        getNewsDetailed(newsId)
    }

    private fun getNewsDetailed(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _detailedNewsResultLiveData.postValue(handleNewsDetail(id))
        }
    }

    private suspend fun handleNewsDetail(id: String): DetailedNewsResult {
        return when (val result = newsRepository.getNewsDetails(id)) {
            is Result.Error -> DetailedNewsResult.ErrorResult(result.result)
            is Result.Success -> DetailedNewsResult.SuccessResult(result.result)
        }
    }

    companion object {
        const val News_ID = "news_id"
        const val News_TITLE = "news_title"
    }
}