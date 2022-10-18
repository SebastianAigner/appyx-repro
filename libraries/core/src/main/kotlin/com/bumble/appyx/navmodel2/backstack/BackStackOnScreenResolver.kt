package com.bumble.appyx.navmodel2.backstack

import com.bumble.appyx.core.navigation.onscreen.OnScreenStateResolver
import com.bumble.appyx.navmodel2.backstack.BackStack.State

object BackStackOnScreenResolver : OnScreenStateResolver<State> {
    override fun isOnScreen(state: State): Boolean =
        true // FIXME only for debug
//        when (state) {
//            State.ACTIVE -> true
//            else -> false
//        }
}
