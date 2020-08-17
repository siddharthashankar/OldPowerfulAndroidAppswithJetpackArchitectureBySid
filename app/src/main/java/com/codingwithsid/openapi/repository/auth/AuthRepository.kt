package com.codingwithsid.openapi.repository.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.switchMap
import com.codingwithsid.openapi.api.auth.OpenApiAuthService
import com.codingwithsid.openapi.api.auth.network_responses.LoginResponse
import com.codingwithsid.openapi.api.auth.network_responses.RegistrationResponse
import com.codingwithsid.openapi.models.AuthToken
import com.codingwithsid.openapi.persistence.AccountPropertiesDao
import com.codingwithsid.openapi.persistence.AuthTokenDao
import com.codingwithsid.openapi.session.SessionManager
import com.codingwithsid.openapi.ui.DataState
import com.codingwithsid.openapi.ui.Response
import com.codingwithsid.openapi.ui.ResponseType
import com.codingwithsid.openapi.ui.auth.state.AuthViewState
import com.codingwithsid.openapi.ui.auth.state.LoginFields
import com.codingwithsid.openapi.util.ApiEmptyResponse
import com.codingwithsid.openapi.util.ApiErrorResponse
import com.codingwithsid.openapi.util.ApiSuccessResponse
import com.codingwithsid.openapi.util.ErrorHandling.Companion.ERROR_UNKNOWN
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

    fun attemptLogin(email: String, password: String): LiveData<DataState<AuthViewState>>{
        return openApiAuthService.login(email, password)
            .switchMap { response ->
                object: LiveData<DataState<AuthViewState>>(){
                    override fun onActive() {
                        super.onActive()
                        when(response){
                            is ApiSuccessResponse ->{
                                value = DataState.data(
                                    AuthViewState(
                                        authToken = AuthToken(response.body.pk, response.body.token)
                                    ),
                                    response = null
                                )
                            }
                            is ApiErrorResponse ->{
                                value = DataState.error(
                                    Response(
                                        message = response.errorMessage,
                                        responseType = ResponseType.Dialog()
                                    )
                                )
                            }
                            is ApiEmptyResponse ->{
                                value = DataState.error(
                                    Response(
                                        message = ERROR_UNKNOWN,
                                        responseType = ResponseType.Dialog()
                                    )
                                )
                            }
                        }
                    }
                }
            }
    }


    fun attemptRegistration(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ): LiveData<DataState<AuthViewState>>{
        return openApiAuthService.register(email, username, password, confirmPassword)
            .switchMap { response ->
                object: LiveData<DataState<AuthViewState>>(){
                    override fun onActive() {
                        super.onActive()
                        when(response){
                            is ApiSuccessResponse ->{
                                value = DataState.data(
                                    AuthViewState(
                                        authToken = AuthToken(response.body.pk, response.body.token)
                                    ),
                                    response = null
                                )
                            }
                            is ApiErrorResponse ->{
                                value = DataState.error(
                                    Response(
                                        message = response.errorMessage,
                                        responseType = ResponseType.Dialog()
                                    )
                                )
                            }
                            is ApiEmptyResponse ->{
                                value = DataState.error(
                                    Response(
                                        message = ERROR_UNKNOWN,
                                        responseType = ResponseType.Dialog()
                                    )
                                )
                            }
                        }
                    }
                }
            }
    }

}

