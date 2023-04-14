package com.bumble.appyx.interactions.core.ui.property.impl

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import com.bumble.appyx.interactions.core.ui.context.UiContext
import com.bumble.appyx.interactions.core.ui.math.lerpFloat
import com.bumble.appyx.interactions.core.ui.property.Interpolatable
import com.bumble.appyx.interactions.core.ui.property.MotionProperty
import com.bumble.appyx.interactions.core.ui.property.impl.RotationZ.Target

class RotationZ(
    uiContext: UiContext,
    target: Target,
    visibilityThreshold: Float = 1f
) : MotionProperty<Float, AnimationVector1D>(
    uiContext = uiContext,
    animatable = Animatable(target.value, Float.VectorConverter),
    easing = target.easing,
    visibilityThreshold = visibilityThreshold
), Interpolatable<Target> {

    class Target(
        val value: Float,
        val easing: Easing? = null,
    )

    override val modifier: Modifier
        get() = Modifier.composed {
            val value by animatable.asState()
            this.graphicsLayer {
                rotationZ = value
            }
        }

    override suspend fun lerpTo(start: Target, end: Target, fraction: Float) {
        snapTo(
            lerpFloat(
                start = start.value,
                end = end.value,
                progress = easingTransform(end.easing, fraction)
            )
        )
    }
}