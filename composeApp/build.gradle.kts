import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
  alias(libs.plugins.androidApplication)
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.composeMultiplatform)
  alias(libs.plugins.composeCompiler)
  alias(libs.plugins.kotlinxSerialization)
}

kotlin {
  androidTarget {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
      jvmTarget.set(JvmTarget.JVM_1_8)
    }
  }

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach { iosTarget ->
    iosTarget.binaries.framework {
      baseName = "ComposeApp"
      isStatic = true
    }
  }

  jvm("desktop")

  @OptIn(ExperimentalWasmDsl::class)
  wasmJs {
    moduleName = "composeApp"
    browser {
      val rootDirPath = project.rootDir.path
      val projectDirPath = project.projectDir.path
      commonWebpackConfig {
        outputFileName = "composeApp.js"
        devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
          static = (static ?: mutableListOf()).apply {
            add(rootDirPath)
            add(projectDirPath)
          }
        }
      }
    }
    binaries.executable()
  }

  sourceSets {
    val desktopMain by getting

    androidMain.dependencies {
      implementation(compose.preview)
      implementation(libs.androidx.activity.compose)
    }
    commonMain.dependencies {
      implementation(projects.lib)
      implementation(compose.material3)
      implementation(compose.components.resources)
      implementation(compose.components.uiToolingPreview)
      implementation(libs.kmp.androidx.lifecycle.viewmodel)
      implementation(libs.kmp.androidx.lifecycle.runtime.compose)
      implementation(libs.kmp.androidx.navigation.compose)
      implementation(libs.kmp.kotlinx.serialization.json)
    }
    desktopMain.dependencies {
      implementation(compose.desktop.currentOs)
    }
  }
}

android {
  namespace = "com.sd.demo.kmp.compose_layer"
  compileSdk = 34
  defaultConfig {
    minSdk = 21
    targetSdk = 34
    applicationId = "com.sd.demo.kmp.compose_layer"
    versionCode = 1
    versionName = "1.0"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
  buildTypes {
    release {
      isMinifyEnabled = false
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
}

dependencies {
  debugImplementation(compose.uiTooling)
}

compose.desktop {
  application {
    mainClass = "com.sd.demo.kmp.compose_layer.MainKt"

    nativeDistributions {
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
      packageName = "com.sd.demo.kmp.compose_layer"
      packageVersion = "1.0.0"
    }
  }
}