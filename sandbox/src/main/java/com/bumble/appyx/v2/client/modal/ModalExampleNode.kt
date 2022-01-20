package com.bumble.appyx.v2.client.modal

import android.os.Parcelable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bumble.appyx.v2.client.child.ChildNode
import com.bumble.appyx.v2.client.modal.ModalExampleNode.Routing
import com.bumble.appyx.v2.client.modal.ModalExampleNode.Routing.Child
import com.bumble.appyx.v2.core.modality.BuildContext
import com.bumble.appyx.v2.core.node.Node
import com.bumble.appyx.v2.core.node.ParentNode
import com.bumble.appyx.v2.core.routing.source.backstack.BackStack
import kotlinx.parcelize.Parcelize

class ModalExampleNode(
    buildContext: BuildContext,
) : ParentNode<Routing>(
    routingSource = BackStack(Child, buildContext.savedStateMap),
    buildContext = buildContext,
) {

    sealed class Routing : Parcelable {
        @Parcelize
        object Child : Routing()
    }

    override fun resolve(routing: Routing, buildContext: BuildContext): Node =
        when (routing) {
            is Child -> ChildNode("", buildContext)
        }

    @Composable
    override fun View(modifier: Modifier) {
        Text("Modal placeholder")
//        Box(Modifier.fillMaxSize()) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(24.dp),
//            ) {
//                var fullScreenEnabled by remember { mutableStateOf(false) }
//                var lastKey by remember { mutableStateOf<ModalElement<Routing>?>(null) }
//
//                Button(
//                    enabled = !fullScreenEnabled,
//                    onClick = {
//                        fullScreenEnabled = true
//                        lastKey = modal.add(Child(Random.nextInt(9999))).also {
//                            modal.showModal(it.key) }
//                        }
//
//                ) {
//                    Text("Show modal")
//                }
//
//                Button(
//                    enabled = fullScreenEnabled,
//                    onClick = {
//                        lastKey?.let {
//                            modal.fullScreen(it.key)
//                        }
//                    }
//                ) {
//                    Text("Make it fullscreen")
//                }
//            }
//
//            Box(
//                modifier = Modifier.fillMaxSize(),
//                contentAlignment = Alignment.Center
//            ) {
//                placeholder<Routing>()
//            }
//        }
    }
}

@Preview
@Composable
fun ModalPreview() {
    ModalExampleNode(BuildContext.root(null)).Compose()
}