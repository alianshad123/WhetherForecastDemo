package com.anshad.whetherapp.di.main

import com.anshad.whetherapp.data.datasource.MainDataSource
import com.anshad.whetherapp.data.repositories.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DashboardAbstractActivityRetainedModule {

    @Binds
    @ViewModelScoped
    abstract fun mainDataSource(impl: MainDataSource): MainRepository


}