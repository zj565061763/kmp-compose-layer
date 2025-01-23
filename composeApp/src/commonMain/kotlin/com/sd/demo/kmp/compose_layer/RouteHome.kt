package com.sd.demo.kmp.compose_layer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun RouteHome(
  onClickSampleAlignContainer: () -> Unit,
  onClickSampleAlignTarget: () -> Unit,
  onClickSampleDropDown: () -> Unit,
  onClickSampleFixOverflow: () -> Unit,
  onClickSampleTargetOffset: () -> Unit,
  onClickSampleZIndex: () -> Unit,
  onClickSampleListMenu: () -> Unit,
) {
  Scaffold { padding ->
    Column(
      modifier = Modifier.fillMaxSize().padding(padding),
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Button(onClick = onClickSampleAlignContainer) { Text(text = "SampleAlignContainer") }
      Button(onClick = onClickSampleAlignTarget) { Text(text = "SampleAlignTarget") }
      Button(onClick = onClickSampleDropDown) { Text(text = "SampleDropDown") }
      Button(onClick = onClickSampleFixOverflow) { Text(text = "SampleFixOverflow") }
      Button(onClick = onClickSampleTargetOffset) { Text(text = "SampleTargetOffset") }
      Button(onClick = onClickSampleZIndex) { Text(text = "SampleZIndex") }
      Button(onClick = onClickSampleListMenu) { Text(text = "SampleListMenu") }
    }
  }
}