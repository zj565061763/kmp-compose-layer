package com.sd.demo.kmp.compose_layer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sd.lib.kmp.compose_layer.LayerContainer
import com.sd.lib.kmp.compose_layer.LayerState
import com.sd.lib.kmp.compose_layer.layer
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SampleAlignContainer(
  onClickBack: () -> Unit,
) {
  LayerContainer {
    RouteScaffold(
      title = "SampleAlignContainer",
      onClickBack = onClickBack,
    ) {
      Content()
    }
  }
}

@Composable
private fun Content() {
  var attach by remember { mutableStateOf(false) }
  var alignment by remember { mutableStateOf(Alignment.Center) }

  /** 创建Layer，并返回[LayerState] */
  val layerState = layer(
    attach = attach,
    onDetachRequest = { attach = false },
    alignment = alignment,
    debug = true,
  ) {
    Box(modifier = Modifier.safeDrawingPadding()) {
      ColorBox()
    }
  }

  Box(modifier = Modifier.fillMaxSize()) {
    ButtonsBox(
      modifier = Modifier.fillMaxSize(),
      onClick = {
        alignment = it
        attach = true
      }
    )

    // 演示跟踪Layer可见状态动画
    AnimatedVisibility(
      modifier = Modifier.align(Alignment.Center),
      visible = layerState.isVisibleState,
      enter = scaleIn(),
      exit = scaleOut(),
    ) {
      Text(
        text = "Sync visible state",
        color = Color.Red,
      )
    }
  }

  LaunchedEffect(layerState) {
    // 监听Layer生命周期状态
    snapshotFlow { layerState.lifecycleState }
      .collect {
        logMsg { "lifecycleState:${it}" }
      }
  }
}

@Composable
private fun ButtonsBox(
  modifier: Modifier = Modifier,
  onClick: (Alignment) -> Unit,
) {
  Box(modifier = modifier) {
    Button(
      modifier = Modifier.align(Alignment.TopCenter),
      onClick = { onClick(Alignment.TopCenter) },
    ) {
      Text(text = "TopCenter")
    }

    Button(
      modifier = Modifier.align(Alignment.BottomCenter),
      onClick = { onClick(Alignment.BottomCenter) },
    ) {
      Text(text = "BottomCenter")
    }

    Button(
      modifier = Modifier.align(Alignment.CenterStart),
      onClick = { onClick(Alignment.CenterStart) },
    ) {
      Text(text = "CenterStart")
    }

    Button(
      modifier = Modifier.align(Alignment.CenterEnd),
      onClick = { onClick(Alignment.CenterEnd) },
    ) {
      Text(text = "CenterEnd")
    }
  }
}

@Preview
@Composable
private fun Preview() {
  LayerContainer {
    Content()
  }
}