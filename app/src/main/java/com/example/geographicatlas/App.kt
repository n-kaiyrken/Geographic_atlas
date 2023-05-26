package com.example.geographicatlas

import android.app.Application
import com.example.geographicatlas.di.DaggerApplicationComponent

class App: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create()
    }
}