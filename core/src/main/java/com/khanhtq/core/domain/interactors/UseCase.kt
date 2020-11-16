package com.khanhtq.core.domain.interactors

import com.khanhtq.common.state.Resource
import kotlinx.coroutines.flow.Flow

abstract class UseCase<in Param, Type> where Type : Any {

    abstract suspend fun run(param: Param): Flow<Resource<Type>>
}