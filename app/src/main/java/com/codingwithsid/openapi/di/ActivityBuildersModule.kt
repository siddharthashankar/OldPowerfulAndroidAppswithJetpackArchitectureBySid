package com.codingwithsid.openapi.di

import com.codingwithsid.openapi.di.auth.AuthFragmentBuildersModule
import com.codingwithsid.openapi.di.auth.AuthModule
import com.codingwithsid.openapi.di.auth.AuthScope
import com.codingwithsid.openapi.di.auth.AuthViewModelModule
import com.codingwithsid.openapi.ui.auth.AuthActivity
import com.codingwithsid.openapi.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
        modules = [AuthModule::class, AuthFragmentBuildersModule::class, AuthViewModelModule::class]
    )
    abstract fun contributeAuthActivity(): AuthActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}