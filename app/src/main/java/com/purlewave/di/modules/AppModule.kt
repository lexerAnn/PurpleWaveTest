package com.purlewave.di.modules

import android.content.Context
import android.provider.Settings
import com.purlewave.data.local.preferences.SharedPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesSharedPrefs(@ApplicationContext context: Context): SharedPref = SharedPref(context)

}