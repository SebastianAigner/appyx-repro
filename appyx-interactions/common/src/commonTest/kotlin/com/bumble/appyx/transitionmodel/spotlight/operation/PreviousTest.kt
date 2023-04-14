package com.bumble.appyx.transitionmodel.spotlight.operation

import com.bumble.appyx.InteractionTarget
import com.bumble.appyx.InteractionTarget.Child1
import com.bumble.appyx.InteractionTarget.Child2
import com.bumble.appyx.InteractionTarget.Child3
import com.bumble.appyx.InteractionTarget.Child4
import com.bumble.appyx.InteractionTarget.Child5
import com.bumble.appyx.InteractionTarget.Child6
import com.bumble.appyx.interactions.core.asElement
import com.bumble.appyx.transitionmodel.spotlight.SpotlightModel
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class PreviousTest {

    @Test
    fun GIVEN_on_first_position_WHEN_previous_is_invoked_THEN_operation_is_not_applicable() {
        val state = SpotlightModel.State(
            positions = listOf(
                SpotlightModel.State.Position(
                    mapOf(
                        Child1.asElement() to SpotlightModel.State.ElementState.STANDARD,
                        Child2.asElement() to SpotlightModel.State.ElementState.STANDARD,
                        Child3.asElement() to SpotlightModel.State.ElementState.STANDARD,
                    )
                ),
                SpotlightModel.State.Position(
                    mapOf(
                        Child4.asElement() to SpotlightModel.State.ElementState.STANDARD,
                        Child5.asElement() to SpotlightModel.State.ElementState.STANDARD,
                        Child6.asElement() to SpotlightModel.State.ElementState.STANDARD,
                    )
                )
            ),
            activeIndex = 0f
        )

        val previous = Previous<InteractionTarget>()

        assertFalse(previous.isApplicable(state))
    }

    @Test
    fun GIVEN_on_last_position_AND_has_previous_WHEN_previous_invoked_THEN_it_will_decrement_the_active_index() {
        val state = SpotlightModel.State(
            positions = listOf(
                SpotlightModel.State.Position(
                    mapOf(
                        Child1.asElement() to SpotlightModel.State.ElementState.STANDARD,
                        Child2.asElement() to SpotlightModel.State.ElementState.STANDARD,
                        Child3.asElement() to SpotlightModel.State.ElementState.STANDARD,
                    )
                ),
                SpotlightModel.State.Position(
                    mapOf(
                        Child4.asElement() to SpotlightModel.State.ElementState.STANDARD,
                        Child5.asElement() to SpotlightModel.State.ElementState.STANDARD,
                        Child6.asElement() to SpotlightModel.State.ElementState.STANDARD,
                    )
                )
            ),
            activeIndex = 2f
        )

        val previous = Previous<InteractionTarget>()

        assertEquals(
            actual = previous.invoke(state).targetState.activeIndex,
            expected = 1f
        )
    }
}