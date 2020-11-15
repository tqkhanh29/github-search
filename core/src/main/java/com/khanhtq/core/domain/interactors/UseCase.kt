package com.khanhtq.core.domain.interactors

import androidx.lifecycle.LiveData
import com.khanhtq.common.state.Resource

abstract class UseCase<in Param, Type> where Type: Any {

    abstract suspend fun run(param: Param): LiveData<Resource<Type>>
}