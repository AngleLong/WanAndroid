# 整个项目采用组件化开发

## 关于Arouter的使用.

1. 每个model都要添加相应的注解
2. 关于kotlin混合的项目要添加

```kotlin
    kapt {
    arguments {
        arg("AROUTER_MODULE_NAME", project.getName())
    }
}
```

## 关于沉浸式状态栏

1. 不用设置颜色.直接设置一个0dp的View

## [Arouter](https://github.com/alibaba/ARouter/blob/master/README_CN.md)

1. [使用 ARouter 实现登录拦截](https://lishide.github.io/2020/06/05/Android-ARouter-Login-Interceptor/)

## 组件化

1.[“终于懂了” 系列：Android组件化，全面掌握！ | 掘金技术征文-双节特别篇](https://juejin.cn/post/6881116198889586701#heading-6)
2.[Android组件化之Application](https://juejin.cn/post/6844904031668666376#heading-1)
3.[Android 组件化最佳实践](https://juejin.cn/post/6844903649102004231#heading-4)

## [一个 Android MVVM 组件化架构框架](https://juejin.cn/post/6866628586414997512#heading-11)

## [屏幕适配->AndroidAutoSize](https://github.com/JessYanCoding/AndroidAutoSize)

## [引导页->Android仿京东到家引导页炫酷动画效果](https://jishuin.proginn.com/p/763bfbd5535f)

## [工具->AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode)

## [侧滑返回->BGASwipeBackLayout-Android](https://github.com/bingoogolapple/BGASwipeBackLayout-Android)

## [material 的ui库使用](https://material.io/)

## [wan android Api](https://www.wanandroid.com/blog/show/2)

## Hilt在多组件中的使用注意事项

    1. 相应的模块添加

```groovy
    id 'kotlin-kapt'
id 'dagger.hilt.android.plugin'
```

    2.引入依赖

```groovy
    implementation rootProject.depsLibs.hilt_android
kapt rootProject.depsLibs.hilt_android_compiler
```

    3. default中添加

```groovy
    defaultConfig {
    minSdk rootProject.android.minSdk
    targetSdk rootProject.android.targetSdk
    versionCode rootProject.android.versionCode
    versionName rootProject.android.versionName

    javaCompileOptions {
        annotationProcessorOptions {
            //arguments = [AROUTER_MODULE_NAME: project.getName()]
            //arouter 为=号 要换成+=
            arguments += [AROUTER_MODULE_NAME: project.getName()]
        }
    }

    kapt {
        javacOptions {
            option("-Adagger.hilt.android.internal.disableAndroidSuperclassValidation=true")
        }
        arguments {
            arg("AROUTER_MODULE_NAME", project.getName())
        }
    }
}
```