package com.sd.demo.kmp.compose_layer

import kotlinx.serialization.Serializable

sealed interface AppRoute {
  @Serializable
  data object Home : AppRoute

  @Serializable
  data object SampleAlignContainer : AppRoute

  @Serializable
  data object SampleAlignTarget : AppRoute

  @Serializable
  data object SampleDropDown : AppRoute

  @Serializable
  data object SampleFixOverflow : AppRoute

  @Serializable
  data object SampleTargetOffset : AppRoute

  @Serializable
  data object SampleZIndex : AppRoute

  @Serializable
  data object SampleListMenu : AppRoute
}