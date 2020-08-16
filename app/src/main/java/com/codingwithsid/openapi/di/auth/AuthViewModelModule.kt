package com.codingwithsid.openapi.di.auth

import androidx.lifecycle.ViewModel
import com.codingwithsid.openapi.di.ViewModelKey
import com.codingwithsid.openapi.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel
}