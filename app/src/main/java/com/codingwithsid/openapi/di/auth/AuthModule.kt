package com.codingwithsid.openapi.di.auth

import com.codingwithsid.openapi.api.auth.OpenApiAuthService
import com.codingwithsid.openapi.persistence.AccountPropertiesDao
import com.codingwithsid.openapi.persistence.AuthTokenDao
import com.codingwithsid.openapi.repository.auth.AuthRepository
import com.codingwithsid.openapi.session.SessionManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {

    // TEMPORARY
    @AuthScope
    @Provides
    fun provideFakeApiService(retrofitBuilder: Retrofit.Builder): OpenApiAuthService{
        return retrofitBuilder
            .build()
            .create(OpenApiAuthService::class.java)
    }

    @AuthScope
    @Provides
    fun provideAuthRepository(
        sessionManager: SessionManager,
        authTokenDao: AuthTokenDao,
        accountPropertiesDao: AccountPropertiesDao,
        openApiAuthService: OpenApiAuthService
    ): AuthRepository{
        return AuthRepository(
        authTokenDao,
        accountPropertiesDao,
        openApiAuthService,
        sessionManager
        )
    }
}