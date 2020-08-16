package com.codingwithsid.openapi.ui.auth

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.codingwithsid.openapi.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import java.lang.Exception
import javax.inject.Inject

abstract class BaseAuthFragment: DaggerFragment() {
    val TAG: String = "AppDebug"

    @Inject
    lateinit var provideFactory: ViewModelProviderFactory

    lateinit var viewModel: AuthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(this, provideFactory).get(AuthViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }
}