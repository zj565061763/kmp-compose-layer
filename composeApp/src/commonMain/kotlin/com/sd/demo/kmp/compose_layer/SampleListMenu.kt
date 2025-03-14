package com.sd.demo.kmp.compose_layer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import androidx.compose.ui.unit.sp
import com.sd.lib.kmp.compose_layer.LayerContainer
import com.sd.lib.kmp.compose_layer.LayerTarget
import com.sd.lib.kmp.compose_layer.SmartAliments
import com.sd.lib.kmp.compose_layer.TargetAlignment
import com.sd.lib.kmp.compose_layer.TargetAlignmentOffset
import com.sd.lib.kmp.compose_layer.TargetLayer
import com.sd.lib.kmp.compose_layer.relativeAlignment

@Composable
fun SampleListMenu(
  onClickBack: () -> Unit,
) {
  LayerContainer {
    RouteScaffold(
      title = "SampleListMenu",
      onClickBack = onClickBack,
    ) {
      Content()
    }
  }
}

@Composable
private fun Content() {
  // 是否添加Layer
  var attach by remember { mutableStateOf(false) }
  // 目标坐标点
  var offset: IntOffset? by remember { mutableStateOf(null) }

  LazyColumn(modifier = Modifier.fillMaxSize()) {
    items(100) { index ->
      ListItem(
        text = index.toString(),
        onOffset = {
          // 保存目标坐标点，并添加Layer
          offset = it
          attach = true
        },
      )
    }
  }

  // Layer
  TargetLayer(
    // 目标坐标点
    target = LayerTarget.Offset(offset),
    // 是否添加Layer
    attach = attach,
    // Layer请求移除回调
    onDetachRequest = { attach = false },
    // 背景颜色透明
    backgroundColor = Color.Transparent,
    // 设置居中对齐
    alignment = TargetAlignment.BottomCenter,
    // 如果默认的对齐方式溢出，会使用[smartAlignments]提供的位置按顺序查找溢出最小的位置
    smartAlignments = SmartAliments.Default,
    /** X轴相对[TargetAlignment]偏移22dp */
    /** X轴相对[TargetAlignment]偏移22dp */
    alignmentOffsetX = TargetAlignmentOffset.DP(22).relativeAlignment(),
    /** Y轴相对[TargetAlignment]偏移22dp */
    /** Y轴相对[TargetAlignment]偏移22dp */
    alignmentOffsetY = TargetAlignmentOffset.DP(22).relativeAlignment(),
    // 调试模式
    debug = true,
  ) {
    // Layer内容
    Menus {
      attach = false
    }
  }
}

@Composable
private fun ListItem(
  modifier: Modifier = Modifier,
  text: String,
  /** 坐标点回调 */
  onOffset: (IntOffset?) -> Unit,
) {
  val onOffsetUpdated by rememberUpdatedState(onOffset)
  var coordinates: LayoutCoordinates? by remember { mutableStateOf(null) }

  Box(
    modifier = modifier
      .fillMaxSize()
      .height(50.dp)
      .onGloballyPositioned {
        coordinates = it
      }
      .pointerInput(Unit) {
        detectTapGestures {
          val offset = coordinates
            ?.localToWindow(it)
            ?.round()
          // 回调坐标点
          onOffsetUpdated(offset)
        }
      }
  ) {
    Text(text = text, modifier = Modifier.align(Alignment.Center))
    HorizontalDivider(modifier = Modifier.align(Alignment.BottomCenter))
  }
}

@Composable
private fun Menus(
  modifier: Modifier = Modifier,
  onClickItem: (String) -> Unit,
) {
  Column(
    modifier = modifier
      .width(200.dp)
      .background(MaterialTheme.colorScheme.surfaceContainer)
  ) {
    remember { listOf("Menu1", "Menu2", "Menu3") }
      .forEach { item ->
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .heightIn(64.dp)
            .clickable { onClickItem(item) }
            .padding(start = 20.dp),
          contentAlignment = Alignment.CenterStart,
        ) {
          Text(item, fontSize = 18.sp)
        }
      }
  }
}