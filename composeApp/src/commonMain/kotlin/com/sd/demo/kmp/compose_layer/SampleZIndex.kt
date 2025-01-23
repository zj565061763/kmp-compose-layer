package com.sd.demo.kmp.compose_layer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sd.lib.kmp.compose_layer.Layer
import com.sd.lib.kmp.compose_layer.LayerContainer
import com.sd.lib.kmp.compose_layer.LayerDetach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SampleZIndex(
  onClickBack: () -> Unit,
) {
  LayerContainer {
    RouteScaffold(
      title = "SampleZIndex",
      onClickBack = onClickBack,
    ) {
      Content()
    }
  }
}

@Composable
private fun Content(
  modifier: Modifier = Modifier,
) {
  var layer1 by remember { mutableStateOf(false) }
  var layer2 by remember { mutableStateOf(false) }

  Layer1(
    attach = layer1,
    onDetachRequest = { layer1 = false },
  )
  Layer2(
    attach = layer2,
    onDetachRequest = { layer2 = false },
  )

  val coroutineScope = rememberCoroutineScope()

  Column(
    modifier = modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Button(onClick = {
      coroutineScope.launch {
        layer1 = true
        delay(1_000)
        layer2 = true
      }
    }) {
      Text("Show")
    }
  }
}

@Composable
private fun Layer1(
  attach: Boolean,
  onDetachRequest: (LayerDetach) -> Unit,
) {
  Layer(
    attach = attach,
    onDetachRequest = onDetachRequest,
    detachOnTouchBackground = true,
    zIndex = 99f,
  ) {
    ColorBox(text = "Layer1", color = Color.Red)
  }
}

@Composable
private fun Layer2(
  attach: Boolean,
  onDetachRequest: (LayerDetach) -> Unit,
) {
  Layer(
    attach = attach,
    onDetachRequest = onDetachRequest,
    detachOnTouchBackground = true,
  ) {
    ColorBox(text = "I am Layer2", color = Color.Blue)
  }
}
