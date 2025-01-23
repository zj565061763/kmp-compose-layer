package com.sd.demo.kmp.compose_layer

import platform.Foundation.NSLog

actual fun logMsg(tag: String, block: () -> String) {
  NSLog("$tag ${block()}")
}