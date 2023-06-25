package com.bumble.appyx.demos.backstack.fader

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.bumble.appyx.components.backstack.BackStack
import com.bumble.appyx.components.backstack.BackStackModel
import com.bumble.appyx.components.backstack.operation.pop
import com.bumble.appyx.components.backstack.operation.push
import com.bumble.appyx.components.backstack.ui.fader.BackStackFader
import com.bumble.appyx.demos.common.AppyxWebSample
import com.bumble.appyx.demos.common.InteractionTarget
import com.bumble.appyx.interactions.core.model.BaseInteractionModel
import com.bumble.appyx.interactions.core.ui.gesture.GestureFactory

@Composable
fun BackStackFaderSample(
    screenWidthPx: Int,
    screenHeightPx: Int,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val model = remember {
        BackStackModel<InteractionTarget>(
            initialTarget = InteractionTarget.Element(),
            savedStateMap = null
        )
    }
    val backStack =
        BackStack(
            scope = coroutineScope,
            model = model,
            motionController = { BackStackFader(it) },
            gestureFactory = { GestureFactory.Noop() }
        )
    val actions = mapOf(
        "Pop" to { backStack.pop() },
        "Push" to { backStack.push(InteractionTarget.Element()) }
    )
    AppyxWebSample(
        screenWidthPx = screenWidthPx,
        screenHeightPx = screenHeightPx,
        interactionModel = backStack.unsafeCast<BaseInteractionModel<InteractionTarget, Any>>(),
        actions = actions,
        modifier = modifier,
    )
}