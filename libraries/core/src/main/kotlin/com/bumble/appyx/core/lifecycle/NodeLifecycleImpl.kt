package com.bumble.appyx.core.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

internal class NodeLifecycleImpl(owner: LifecycleOwner) : NodeLifecycle {

    private val lifecycleRegistry = LifecycleRegistry(owner)

    override val lifecycle: Lifecycle
        get() = lifecycleRegistry

    override fun updateLifecycleState(state: Lifecycle.State) {
        lifecycleRegistry.currentState = state
    }

}
