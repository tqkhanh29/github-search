package com.khanhtq.common

import kotlinx.coroutines.CoroutineDispatcher

interface Executor {
    fun io(): CoroutineDispatcher

    fun default(): CoroutineDispatcher

    fun main(): CoroutineDispatcher
}