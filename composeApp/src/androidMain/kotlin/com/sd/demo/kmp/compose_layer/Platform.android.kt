package com.sd.demo.kmp.compose_layer

import android.util.Log

actual fun logMsg(tag: String, block: () -> String) {
  Log.d(tag, block())
}