package com.bumble.appyx.demos.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumble.appyx.demos.common.InteractionTarget.Element
import com.bumble.appyx.interactions.core.DraggableChildren
import com.bumble.appyx.interactions.core.model.BaseInteractionModel
import com.bumble.appyx.interactions.core.ui.helper.InteractionModelSetup
import com.bumble.appyx.interactions.core.ui.output.ElementUiModel
import kotlin.random.Random

sealed class InteractionTarget {
    data class Element(val idx: Int = Random.nextInt(1, 100)) : InteractionTarget() {
        override fun toString(): String =
            "Element $idx"
    }
}

private val containerShape = RoundedCornerShape(8)

enum class ChildSize {
    SMALL,
    MEDIUM,
    MAX,
}

@Composable
fun AppyxWebSample(
    screenWidthPx: Int,
    screenHeightPx: Int,
    interactionModel: BaseInteractionModel<InteractionTarget, Any>,
    actions: Map<String, () -> Unit>,
    childSize: ChildSize = ChildSize.SMALL,
    modifier: Modifier = Modifier,
) {
    InteractionModelSetup(interactionModel)

    Column(
        modifier = modifier,
        horizontalAlignment = CenterHorizontally,
    ) {
        Box(
            Modifier
                .fillMaxHeight()
                .aspectRatio(0.56f)
                .border(4.dp, color_primary, containerShape)
                .clip(containerShape)
                .weight(0.9f)
        ) {
            Box(Modifier.padding(
                when (childSize) {
                    ChildSize.SMALL -> 32.dp
                    ChildSize.MEDIUM -> 16.dp
                    ChildSize.MAX -> 0.dp
                }
            )) {
                DraggableChildren(
                    interactionModel = interactionModel,
                    screenWidthPx = screenWidthPx,
                    screenHeightPx = screenHeightPx,
                ) { elementUiModel ->
                    ModalUi(
                        elementUiModel = elementUiModel,
                        isChildMaxSize = childSize == ChildSize.MAX,
                        modifier = Modifier.align(Alignment.TopCenter),
                    )
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
                .padding(top = 8.dp),
        ) {
            actions.keys.forEachIndexed { index, key ->
                Action(text = key, action = actions.getValue(key))
                if (index != actions.size - 1) {
                    Spacer(modifier = Modifier.requiredWidth(8.dp))
                }
            }
        }
    }
}

val colors = listOf(
    md_pink_500,
    md_indigo_500,
    md_blue_500,
    md_light_blue_500,
    md_cyan_500,
    md_teal_500,
    md_light_green_500,
    md_lime_500,
    md_amber_500,
    md_grey_500,
    md_blue_grey_500,
)

@Composable
fun ModalUi(
    elementUiModel: ElementUiModel<InteractionTarget>,
    isChildMaxSize: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize(if (isChildMaxSize) 1f else 0.9f)
            .then(elementUiModel.modifier)
            .background(
                color = when (val target = elementUiModel.element.interactionTarget) {
                    is Element -> colors.getOrElse(target.idx % colors.size) { Color.Cyan }
                },
                shape = RoundedCornerShape(if (isChildMaxSize) 0 else 8)
            )
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = elementUiModel.element.interactionTarget.toString(),
            fontSize = 12.sp,
            color = Color.White
        )
    }
}

@Composable
private fun Action(
    text: String,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(color_primary, shape = RoundedCornerShape(4.dp))
            .clickable { action.invoke() }
            .padding(horizontal = 18.dp, vertical = 4.dp)
    ) {
        Text(text)
    }
}