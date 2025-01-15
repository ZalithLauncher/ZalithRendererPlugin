plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    val nameId = "com.movtery.zalithplugin.renderer"

    namespace = nameId
    compileSdk = 34

    signingConfigs {
        create("release") {
            keyAlias = "key0"
            keyPassword = "ZALITH_RENDERER_PLUGIN"
            storeFile = file("plugin-key.jks")
            storePassword = "ZALITH_RENDERER_PLUGIN"
        }
    }

    defaultConfig {
        applicationId = nameId
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
        configureEach {
            //应用名
            //app name
            resValue("string","app_name","XXX Renderer")
            //包名后缀
            //package name Suffix
            applicationIdSuffix = ".xxx"

            //渲染器在启动器内显示的名称
            //The name displayed by the renderer in the launcher
            manifestPlaceholders["des"] = ""
            //渲染器的具体定义 格式为 名称:渲染器库名:EGL库名 例如 Some Renderer:libSome_renderer.so:libSome_renderer.so
            //The specific definition format of a renderer is ${name}:${renderer library name}:${EGL library name}, for example: Some Renderer:libSome_renderer.so:libSome_renderer.so
            manifestPlaceholders["renderer"] = ""

            //特殊Env
            //Special Env
            //DLOPEN=libxxx.so 用于加载额外库文件
            //DLOPEN=libxxx.so used to load external library
            //如果有多个库,可以使用","隔开,例如  DLOPEN=libxxx.so,libyyy.so
            //If there are multiple libraries, you can use "," to separate them, for example  DLOPEN=libxxx.so,libyyy.so
            manifestPlaceholders["pojavEnv"] = mutableMapOf<String,String>().apply {
                //put("POJAV_RENDERER", "renderer_id")
                //put("ENV_KEY", "env_value")
            }.run {
                var env = ""
                forEach { (key, value) ->
                    env += "$key=$value:"
                }
                env.dropLast(1)
            }

            //FCL可以额外使用Boat启动方式，但ZalithLauncher只能使用Pojav这一种启动方式，所以移除了boatEnv环境变量的配置项，因此，这个渲染器插件就不能为FCL提供支持！
            //FCL can additionally use the Boat launch method, but ZalithLauncher can only use the Pojav launch method.
            //As a result, the boatEnv environment variable configuration option was removed. Therefore, this renderer plugin cannot provide support for FCL!

            //manifestPlaceholders["boatEnv"] = mutableMapOf<String,String>().apply {

            //}.run {
            //    var env = ""
            //    forEach { (key, value) ->
            //        env += "$key=$value:"
            //    }
            //    env.dropLast(1)
            //}
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
}