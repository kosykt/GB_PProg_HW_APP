import org.gradle.api.JavaVersion

object Modules {
    const val domain = ":domain"
    const val data = ":data"
    const val mytimer = ":mytimer"
}

object Config {
    const val application_id = "com.example.gb_pprog"
    const val compile_sdk = 31
    const val min_sdk = 27
    const val target_sdk = 31
    const val jvm_target = "1.8"
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Versions {

    //Glide
    const val glide = "4.11.0"

    //Room
    const val room = "2.4.1"

    //Jetpack Navigation
    const val jetNav = "2.3.5"

    //Kotlin coroutines
    const val coroutinesCore = "1.5.2"
    const val coroutinesAndroid = "1.5.2"

    //Lifecycle KTX
    const val lifecycleViewModel = "2.4.0"
    const val lifecycleRunTime = "2.4.0"

    //Koin
    const val koin = "3.1.3"

    //Retrofit
    const val retrofit = "2.9.0"

    //Kotlin ktx
    const val kotlinCoreKtx = "1.7.0"

    //Appcompat
    const val appCompat = "1.4.0"

    //Material
    const val material = "1.4.0"

    //constraintLayout
    const val constraintLayout = "2.1.2"

    //Test
    const val jUnit = "4.13.2"
    const val extjUnit = "1.1.3"
    const val espressoCore = "3.4.0"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
}

object Room {
    const val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"
}

object JetpackNavigation {
    const val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.jetNav}"
    const val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.jetNav}"
}

object Coroutines {
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
}

object LifecycleComponents {
    const val viewmodel_ktx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModel}"
    const val runtime_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRunTime}"
}

object Koin {
    const val koin_core = "io.insert-koin:koin-core:${Versions.koin}"
    const val koin_android = "io.insert-koin:koin-android:${Versions.koin}"
    const val koin_test = "io.insert-koin:koin-test:${Versions.koin}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
}

object Kotlin {
    const val kotlin_cote_ktx = "androidx.core:core-ktx:${Versions.kotlinCoreKtx}"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraint_layout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
}

object TestImpl {
    const val junit = "junit:junit:${Versions.jUnit}"
    const val ext_junit = "androidx.test.ext:junit:${Versions.extjUnit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}