package kg.optima.mobile.base.presentation.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Конвертация Flow для натив
 */
fun <T> Flow<T>.asCommonFlow(): CommonFlow<T> = CommonFlow(this)

/**
 * Flow для использования в нативе
 */
class CommonFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {
    fun watch(block: (T) -> Unit): Closeable {
        val job = Job()
        onEach { block(it) }.launchIn(CoroutineScope(job + Dispatchers.Main))

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}
