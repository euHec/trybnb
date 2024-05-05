package com.betrybe.trybnb.ui.views.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.fragment.app.Fragment
import com.betrybe.trybnb.R
import com.betrybe.trybnb.common.ApiIdlingResource
import com.betrybe.trybnb.data.config.ApiClientService
import com.betrybe.trybnb.data.models.AuthenticationRequest
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import java.lang.Exception

class Fragment_profile : Fragment() {

    private val apiService = ApiClientService.instance

    @SuppressLint("RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val profileInputEmail: TextInputLayout = view.findViewById(R.id.login_input_profile)
        val profileInputPassword: TextInputLayout = view.findViewById(R.id.password_input_profile)
        val profileButtonLogin: Button = view.findViewById(R.id.login_button_profile)


        val username = profileInputEmail.editText!!
        val password = profileInputPassword.editText!!

        profileButtonLogin.setOnClickListener {
            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)

            val isTrue = username.text.isNotEmpty()
            val isValidPassword = password.text.isNotEmpty()

            if (!isTrue || !isValidPassword) {
                if (!isTrue) profileInputEmail.error = getString(R.string.email_warning)
                if (!isValidPassword) profileInputPassword.error = getString(R.string.password_warning)
                return@setOnClickListener
            }
            profileInputEmail.error = null
            profileInputPassword.error = null

            CoroutineScope(IO).launch {
                try {
                    ApiIdlingResource.increment()
                    val body = AuthenticationRequest(username.text.toString(), password.text.toString())
                    val responseLogin = apiService.authenticate(body)
                    if (responseLogin.isSuccessful) {
                        CoroutineScope(Main).launch {
                            val snackbar = Snackbar.make(view, R.string.login_succeeded, Snackbar.LENGTH_LONG)
                            snackbar.show()
                        }
                    }
                    ApiIdlingResource.decrement()
                } catch (e: Exception) {
                    ApiIdlingResource.decrement()
                    Log.d("APP_ERROR", e.message.toString())
                    CoroutineScope(Main).launch {
                        val snackbar = Snackbar.make(view, e.message.toString(), Snackbar.LENGTH_LONG)
                        snackbar.show()
                    }
                }
            }
        }
        return view
    }
}