package com.trickring.app_flux.flux

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.trickring.app_flux.App
import kotlinx.coroutines.CoroutineScope

/**
 * Store
 *
 * @param app Application Class
 */
abstract class Store(app: App) : AndroidViewModel(app) {

    /**
     * Application Class
     */
    val application: App get() = getApplication()

    /**
     * Store Scope
     */
    val scope: CoroutineScope get() = viewModelScope
}
