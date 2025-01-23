package com.sd.lib.kmp.compose_layer

import androidx.compose.runtime.Composable
import platform.Foundation.NSLog

@Composable
internal actual fun PBackHandler(onBack: () -> Unit) {
}

internal actual fun pLogMsg(block: () -> String) {
  NSLog("FLayer ${block()}")
}