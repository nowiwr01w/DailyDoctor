package com.nowiwr01p.model.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin(
    modules: List<Module>,
    platformCallback: KoinApplication.() -> Unit = {}
): KoinApplication {
    return startKoin {
        modules(modules)
        platformCallback()
    }
}