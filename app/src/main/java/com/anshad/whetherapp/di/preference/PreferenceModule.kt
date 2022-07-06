package com.anshad.whetherapp.di.preference

import com.anshad.whetherapp.data.localdata.pref.DefaultPreference
import com.anshad.whetherapp.data.localdata.pref.PreferenceProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {

    @Singleton
    @Binds
    abstract fun provideDefaultPreference(impl: DefaultPreference): PreferenceProvider
}