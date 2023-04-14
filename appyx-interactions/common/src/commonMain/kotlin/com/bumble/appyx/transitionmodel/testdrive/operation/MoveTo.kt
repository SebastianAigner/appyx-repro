package com.bumble.appyx.transitionmodel.testdrive.operation

import androidx.compose.animation.core.AnimationSpec
import com.bumble.appyx.interactions.Parcelize
import com.bumble.appyx.interactions.core.model.transition.BaseOperation
import com.bumble.appyx.interactions.core.model.transition.Operation
import com.bumble.appyx.transitionmodel.testdrive.TestDrive
import com.bumble.appyx.transitionmodel.testdrive.TestDriveModel

@Parcelize
data class MoveTo<InteractionTarget>(
    private val elementState: TestDriveModel.State.ElementState,
    override val mode: Operation.Mode = Operation.Mode.KEYFRAME
) : BaseOperation<TestDriveModel.State<InteractionTarget>>() {

    override fun isApplicable(state: TestDriveModel.State<InteractionTarget>): Boolean =
        state.elementState.next() == elementState

    override fun createFromState(baseLineState: TestDriveModel.State<InteractionTarget>): TestDriveModel.State<InteractionTarget> =
        baseLineState

    override fun createTargetState(fromState: TestDriveModel.State<InteractionTarget>): TestDriveModel.State<InteractionTarget> =
        fromState.copy(
            elementState = fromState.elementState.next()
        )
}

fun <InteractionTarget : Any> TestDrive<InteractionTarget>.moveTo(
    elementState: TestDriveModel.State.ElementState,
    mode: Operation.Mode = Operation.Mode.KEYFRAME,
    animationSpec: AnimationSpec<Float> = defaultAnimationSpec
) {
    operation(MoveTo(elementState, mode), animationSpec)
}