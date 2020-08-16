package com.codingwithsid.openapi.di

import androidx.lifecycle.ViewModelProvider
import com.codingwithsid.openapi.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}