package com.codingwithsid.openapi.repository.auth

import androidx.lifecycle.LiveData
import com.codingwithsid.openapi.api.auth.OpenApiAuthService
import com.codingwithsid.openapi.api.auth.network_responses.LoginResponse
import com.codingwithsid.openapi.api.auth.network_responses.RegistrationResponse
import com.codingwithsid.openapi.persistence.AccountPropertiesDao
import com.codingwithsid.openapi.persistence.AuthTokenDao
import com.codingwithsid.openapi.session.SessionManager
import com.codingwithsid.openapi.util.GenericApiResponse
import javax.inject.Inject

class AuthRepository
@Inject
constructor(
    val authTokenDao: AuthTokenDao,
    val accountPropertiesDao: AccountPropertiesDao,
    val openApiAuthService: OpenApiAuthService,
    val sessionManager: SessionManager
)
{

    fun testLoginRequest(email: String, password: String): LiveData<GenericApiResponse<LoginResponse>>{
        return openApiAuthService.login(email, password)
    }

    fun testRegistrationRequest(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ): LiveData<GenericApiResponse<RegistrationResponse>>{
        return openApiAuthService.register(email, username, password, confirmPassword)
    }
}