package com.sd.lib.kmp.compose_layer

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

@Composable
internal actual fun PBackHandler(onBack: () -> Unit) {
  BackHandler(onBack = onBack)
}

internal actual fun pLogMsg(block: () -> String) {
  Log.d("Flayer", block())
}