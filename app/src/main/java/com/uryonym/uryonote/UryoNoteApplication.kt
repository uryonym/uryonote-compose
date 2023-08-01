package com.uryonym.uryonote

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class UryoNoteApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}