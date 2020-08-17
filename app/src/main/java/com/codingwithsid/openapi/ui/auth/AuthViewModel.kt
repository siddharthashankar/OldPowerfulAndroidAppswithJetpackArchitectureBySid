package com.codingwithsid.openapi.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.codingwithsid.openapi.api.auth.network_responses.LoginResponse
import com.codingwithsid.openapi.api.auth.network_responses.RegistrationResponse
import com.codingwithsid.openapi.repository.auth.AuthRepository
import com.codingwithsid.openapi.util.GenericApiResponse
import javax.inject.Inject

import androidx.lifecycle.*
import com.codingwithsid.openapi.models.AuthToken
import com.codingwithsid.openapi.ui.BaseViewModel
import com.codingwithsid.openapi.ui.DataState
import com.codingwithsid.openapi.ui.auth.state.*
import com.codingwithsid.openapi.ui.auth.state.AuthStateEvent.*
import com.codingwithsid.openapi.util.AbsentLiveData

class AuthViewModel
@Inject
constructor(
    val authRepository: AuthRepository
): BaseViewModel<AuthStateEvent, AuthViewState>()
{
    override fun handleStateEvent(stateEvent: AuthStateEvent): LiveData<DataState<AuthViewState>> {
        when(stateEvent){

            is LoginAttemptEvent -> {
                return authRepository.attemptLogin(
                    stateEvent.email,
                    stateEvent.password
                )
            }

            is RegisterAttemptEvent -> {
                return authRepository.attemptRegistration(
                    stateEvent.email,
                    stateEvent.username,
                    stateEvent.password,
                    stateEvent.confirm_password
                )
            }

            is CheckPreviousAuthEvent -> {
                return AbsentLiveData.create()
            }


        }
    }

    override fun initNewViewState(): AuthViewState {
        return AuthViewState()
    }

    fun setRegistrationFields(registrationFields: RegistrationFields){
        val update = getCurrentViewStateOrNew()
        if(update.registrationFields == registrationFields){
            return
        }
        update.registrationFields = registrationFields
        _viewState.value = update
    }

    fun setLoginFields(loginFields: LoginFields){
        val update = getCurrentViewStateOrNew()
        if(update.loginFields == loginFields){
            return
        }
        update.loginFields = loginFields
        _viewState.value = update
    }

    fun setAuthToken(authToken: AuthToken){
        val update = getCurrentViewStateOrNew()
        if(update.authToken == authToken){
            return
        }
        update.authToken = authToken
        _viewState.value = update
    }
}