package com.khanhtq.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.khanhtq.common.state.Resource
import com.khanhtq.common.state.Status
import kotlinx.coroutines.CoroutineDispatcher

fun <T, A> performGetOperation(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Resource<A>,
    saveCallResult: suspend (A) -> Unit,
    dispatcher: CoroutineDispatcher
): LiveData<Resource<T>> = liveData(dispatcher) {
    emit(Resource.loading())
    val source = databaseQuery().map { Resource.success(it) }
    emitSource(source)

    val responseStatus = networkCall()
    if (responseStatus.status == Status.SUCCESS) {
        saveCallResult(responseStatus.data!!)
    } else if (responseStatus.status == Status.ERROR) {
        emit(Resource.error(responseStatus.message ?: "Unknown Error"))
    }
}