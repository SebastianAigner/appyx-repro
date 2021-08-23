package com.github.zsoltk.composeribs.core.routing

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface TransitionHandler<S> {

    @Composable
    fun handle(
        fromState: S,
        toState: S,
        onTransitionOffScreenFinished: () -> Unit,
        onTransitionRemoveFinished: () -> Unit,
    ): Modifier
}
