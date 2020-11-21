package com.khanhtq.core.domain.interactors

import androidx.lifecycle.LiveData
import com.khanhtq.common.state.Resource
import kotlinx.coroutines.flow.Flow

interface UseCase<in Param, Type>{

    fun run(param: Param): LiveData<Resource<Type>>
}