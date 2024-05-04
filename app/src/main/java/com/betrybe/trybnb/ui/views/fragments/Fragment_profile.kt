package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.betrybe.trybnb.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class Fragment_profile : Fragment() {
    private fun validarEmail(email: Editable): Boolean {
        val regex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z]+\\.[A-Za-z]{2,}")
        return regex.matches(email)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val profileInputEmail: TextInputLayout = view.findViewById(R.id.login_input_profile)
        val profileInputPassword: TextInputLayout = view.findViewById(R.id.password_input_profile)
        val profileButtonLogin: Button = view.findViewById(R.id.login_button_profile)

        val email = profileInputEmail.editText!!
        val password = profileInputPassword.editText!!

        profileButtonLogin.setOnClickListener {
            val isTrue = validarEmail(email.text)
            val isValidPassword = password.text.isNotEmpty()
            Log.i("RETURN-APP", "$isValidPassword")

            if (!isTrue || !isValidPassword) {
                if (!isTrue) profileInputEmail.error = getString(R.string.email_warning)
                if (!isValidPassword) profileInputPassword.error = getString(R.string.password_warning)
                return@setOnClickListener
            }
            profileInputEmail.error = null
            profileInputPassword.error = null
        }
        return view
    }
}