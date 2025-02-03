package ru.sicampus.bootcamp2025.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> Flow<T>.collectionWithLifecycle(
    fragment: CoroutineScope,
    function: (T) -> Unit
){
    fragment.viewLifecycleOwner.lifecycleScope.launch {
        fragment.repeatOnLifecycle(Lifecycle.State.STARTED){
           collect{ function.invoke(it)

            }
        }
    }
}
