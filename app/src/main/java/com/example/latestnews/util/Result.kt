package com.example.latestnews.util

import com.example.latestnews.domain.news.ItemNews
import com.example.latestnews.domain.news.News
import kotlin.coroutines.cancellation.CancellationException

sealed class Result<out S, out E> {

    data class Success<out S>(val result: S) : Result<S, Nothing>()

    data class Error<out E>(val result: E) : Result<Nothing, E>()
}

inline fun <S, R> S.runOperationCatching(block: S.() -> R): Result<R, Throwable> {
    return try {
        Result.Success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        Result.Error(e)
    }
}

inline fun <S, E, R> Result<S, E>.mapSuccess(block: (S) -> R): Result<R, E> =
    when (this) {
        is Result.Success -> Result.Success(result = block(this.result))
        is Result.Error -> Result.Error(result = this.result)
    }

inline fun <S, E> Result<S, E>.doOnSuccess(block: (S) -> Unit): Result<S, E> {
    if (this is Result.Success) {
        block(this.result)
    }
    return this
}

sealed class NewsResult {
    object Loading : NewsResult()
    object EmptyResult : NewsResult()
    data class SuccessResult(val result: List<ItemNews>) : NewsResult()
    data class ErrorResult(val e: Throwable) : NewsResult()
}

sealed class DetailedNewsResult {
    data class Loading(val title: String) : DetailedNewsResult()
    data class SuccessResult(val result: News) : DetailedNewsResult()
    data class ErrorResult(val e: Throwable) : DetailedNewsResult()
}