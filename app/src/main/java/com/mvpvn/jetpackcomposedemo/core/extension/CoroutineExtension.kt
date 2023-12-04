package com.mvpvn.jetpackcomposedemo.core.extension

import android.os.SystemClock
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

fun <T> Flow<T>.observeFlow(
    owner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.CREATED,
    action: suspend (T) -> Unit
) {
    flowWithLifecycle(owner.lifecycle, minActiveState)
        .onEach(action)
        .launchIn(owner.lifecycleScope)
}

fun <T> MutableSharedFlow<T>.observeSharedFlow(
    owner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.CREATED,
    action: suspend (T) -> Unit
) {
    asSharedFlow()
        .flowWithLifecycle(owner.lifecycle, minActiveState)
        .onEach(action)
        .launchIn(owner.lifecycleScope)
}

fun <T> Channel<T>.observeChannel(
    owner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.CREATED,
    action: suspend (T) -> Unit
) {
    receiveAsFlow().observeFlow(owner, minActiveState, action)
}

inline fun <reified T> eventSharedFlow() =
    MutableSharedFlow<T>(extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

inline fun <reified T> eventFlow() = Channel<T>(onBufferOverflow = BufferOverflow.DROP_LATEST)

inline fun <reified T> stateFlow(initialValue: T) = MutableStateFlow(initialValue)

suspend fun <T> ioContext(block: suspend CoroutineScope.() -> T) =
    withContext(Dispatchers.IO, block)

suspend fun <T> mainContext(block: suspend CoroutineScope.() -> T) =
    withContext(Dispatchers.Main, block)

suspend fun <T> defaultContext(block: suspend CoroutineScope.() -> T) =
    withContext(Dispatchers.Default, block)

suspend fun <T> withDelay(timeMillis: Long, block: suspend () -> T): T {
    val startTime = SystemClock.elapsedRealtime()
    val result = block()
    val measureTime = SystemClock.elapsedRealtime() - startTime
    if (measureTime < timeMillis) {
        delay(timeMillis - measureTime)
    }
    return result
}
