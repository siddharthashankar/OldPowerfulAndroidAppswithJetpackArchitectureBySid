package com.codingwithsid.openapi.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.codingwithsid.openapi.R
import com.codingwithsid.openapi.models.AuthToken
import com.codingwithsid.openapi.ui.auth.state.LoginFields
import com.codingwithsid.openapi.util.ApiEmptyResponse
import com.codingwithsid.openapi.util.ApiErrorResponse
import com.codingwithsid.openapi.util.ApiSuccessResponse
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseAuthFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "LoginFragment: ${viewModel}")

        subscribeObservers()

        login_button.setOnClickListener {
            viewModel.setAuthToken(
                AuthToken(
                    1,
                    "gdfngidfng4nt43n43jn34jn"
                )
            )
        }
    }


    fun subscribeObservers(){
        viewModel.viewState.observe(viewLifecycleOwner, Observer{
            it.loginFields?.let{
                it.login_email?.let{input_email.setText(it)}
                it.login_password?.let{input_password.setText(it)}
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.setLoginFields(
            LoginFields(
                input_email.text.toString(),
                input_password.text.toString()
            )
        )
    }

}




