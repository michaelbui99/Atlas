package io.github.michaelbui99.atlas.model.util

import android.annotation.SuppressLint
import android.content.Context
import java.lang.IllegalStateException

class ApplicationContextProvider private constructor() {
    private var context: Context? = null

    fun setContext(context: Context){
        this.context = context.applicationContext
    }

    fun getContext(): Context{
        if (context==null){
            throw IllegalStateException("Application Context has not been set yet")
        }

        return this.context!!
    }

    companion object {
        // Context is application context, so this should not lead to any memory leaks (?)
        // since application context lasts the whole application lifecycle
        @SuppressLint("StaticFieldLeak")
        private var instance: ApplicationContextProvider? = null

        @Synchronized
        fun getInstance(): ApplicationContextProvider {
            if (instance == null) {
                instance = ApplicationContextProvider()
            }

            return instance!!
        }
    }
}