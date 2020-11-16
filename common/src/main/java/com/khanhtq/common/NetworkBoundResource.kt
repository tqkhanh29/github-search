package com.khanhtq.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.khanhtq.common.state.Resource

fun <OriginalType, ResultType> performGetOperation(
    databaseQuery: () -> LiveData<OriginalType>,
    networkCall: () -> LiveData<Resource<OriginalType>>,
    saveCallResult: suspend (OriginalType) -> Unit,
    mapper: (OriginalType) -> ResultType,
    executor: Executor
): LiveData<Resource<ResultType>> = liveData() {
}