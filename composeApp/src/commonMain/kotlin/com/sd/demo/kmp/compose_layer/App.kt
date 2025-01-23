package com.sd.demo.kmp.compose_layer

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun App() {
  MaterialTheme {
    val navController = rememberNavController()
    NavHost(
      navController = navController,
      startDestination = AppRoute.Home,
    ) {
      composable<AppRoute.Home> {
        RouteHome(
          onClickSampleAlignContainer = { navController.navigate(AppRoute.SampleAlignContainer) },
          onClickSampleAlignTarget = { navController.navigate(AppRoute.SampleAlignTarget) },
          onClickSampleDropDown = { navController.navigate(AppRoute.SampleDropDown) },
          onClickSampleFixOverflow = { navController.navigate(AppRoute.SampleFixOverflow) },
          onClickSampleTargetOffset = { navController.navigate(AppRoute.SampleTargetOffset) },
          onClickSampleZIndex = { navController.navigate(AppRoute.SampleZIndex) },
          onClickSampleListMenu = { navController.navigate(AppRoute.SampleListMenu) },
        )
      }
      composable<AppRoute.SampleAlignContainer> {
        SampleAlignContainer(onClickBack = { navController.popBackStack() })
      }
      composable<AppRoute.SampleAlignTarget> {
        SampleAlignTarget(onClickBack = { navController.popBackStack() })
      }
      composable<AppRoute.SampleDropDown> {
        SampleDropDown(onClickBack = { navController.popBackStack() })
      }
      composable<AppRoute.SampleFixOverflow> {
        SampleFixOverflow(onClickBack = { navController.popBackStack() })
      }
      composable<AppRoute.SampleTargetOffset> {
        SampleTargetOffset(onClickBack = { navController.popBackStack() })
      }
      composable<AppRoute.SampleZIndex> {
        SampleZIndex(onClickBack = { navController.popBackStack() })
      }
      composable<AppRoute.SampleListMenu> {
        SampleListMenu(onClickBack = { navController.popBackStack() })
      }
    }
  }
}

expect fun logMsg(tag: String = "kmp-compose-layer", block: () -> String)