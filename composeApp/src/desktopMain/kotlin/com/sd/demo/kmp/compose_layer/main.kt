package com.sd.demo.kmp.compose_layer

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
  Window(
    onCloseRequest = ::exitApplication,
    title = "kmp-compose-layer",
  ) {
    App()
  }
}