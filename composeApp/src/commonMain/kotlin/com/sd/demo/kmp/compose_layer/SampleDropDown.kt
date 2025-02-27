package com.sd.demo.kmp.compose_layer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sd.lib.kmp.compose_layer.Directions
import com.sd.lib.kmp.compose_layer.LayerContainer
import com.sd.lib.kmp.compose_layer.LayerTarget
import com.sd.lib.kmp.compose_layer.TargetAlignment
import com.sd.lib.kmp.compose_layer.TargetLayer
import com.sd.lib.kmp.compose_layer.layerTag

@Composable
fun SampleDropDown(
  onClickBack: () -> Unit,
) {
  LayerContainer {
    RouteScaffold(
      title = "SampleDropDown",
      onClickBack = onClickBack,
    ) {
      Content()
    }
  }
}

@Composable
private fun Content() {
  val tag = "hello"
  var attach by remember { mutableStateOf(false) }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(horizontal = 10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Spacer(modifier = Modifier.height(300.dp))
    Button(
      onClick = { attach = !attach },
      modifier = Modifier.layerTag(tag)
    ) {
      Text(if (attach) "Close" else "Open")
    }
  }

  TargetLayer(
    target = LayerTarget.Tag(tag),
    attach = attach,
    onDetachRequest = { attach = false },
    alignment = TargetAlignment.BottomCenter,
    clipBackgroundDirection = Directions.Top,
    debug = true,
  ) {
    VerticalList(
      count = 5,
      modifier = Modifier.width(200.dp),
    )
  }
}