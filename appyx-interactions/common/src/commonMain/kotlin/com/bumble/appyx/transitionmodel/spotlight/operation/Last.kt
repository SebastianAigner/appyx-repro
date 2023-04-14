package com.bumble.appyx.transitionmodel.spotlight.operation

import androidx.compose.animation.core.AnimationSpec
import com.bumble.appyx.interactions.Parcelize
import com.bumble.appyx.interactions.core.model.transition.BaseOperation
import com.bumble.appyx.interactions.core.model.transition.Operation
import com.bumble.appyx.transitionmodel.spotlight.Spotlight
import com.bumble.appyx.transitionmodel.spotlight.SpotlightModel


@Parcelize
class Last<InteractionTarget : Any>(
    override val mode: Operation.Mode = Operation.Mode.GEOMETRY
) : BaseOperation<SpotlightModel.State<InteractionTarget>>() {

    override fun isApplicable(state: SpotlightModel.State<InteractionTarget>): Boolean =
        true

    override fun createFromState(baseLineState: SpotlightModel.State<InteractionTarget>): SpotlightModel.State<InteractionTarget> =
        baseLineState

    override fun createTargetState(fromState: SpotlightModel.State<InteractionTarget>): SpotlightModel.State<InteractionTarget> =
        fromState.copy(
            activeIndex = fromState.positions.lastIndex.toFloat(),
        )
}

fun <InteractionTarget : Any> Spotlight<InteractionTarget>.last(
    animationSpec: AnimationSpec<Float> = defaultAnimationSpec,
    mode: Operation.Mode = Operation.Mode.GEOMETRY
) {
    operation(Last(mode), animationSpec)
}