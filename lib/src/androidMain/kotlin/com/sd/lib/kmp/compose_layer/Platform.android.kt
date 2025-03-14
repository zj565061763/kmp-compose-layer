package com.sd.lib.kmp.compose_layer

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

@Composable
internal actual fun PBackHandler(onBack: () -> Unit) {
  BackHandler(onBack = onBack)
}