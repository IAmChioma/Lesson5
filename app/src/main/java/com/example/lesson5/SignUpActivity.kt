package com.example.lesson5

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.signup_layout.*
import kotlinx.android.synthetic.main.signup_layout.view.*

class SignUpActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_layout)

        inner_layout?.signUp?.setOnClickListener() {
            val firstName = firstname.text.toString().trim()
            val lastName = lastname.text.toString().trim()
            val userName = signUpEmail.text.toString().trim()
            val password = signUpPassword.text.toString().trim()

            if (firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty() || password.isEmpty()) {
                if (firstName.isEmpty())
                    firstname.error = "Please enter First name"

                if (lastName.isEmpty())
                    lastname.error = "Please enter Last name"

                if (userName.isEmpty())
                    signUpEmail.error = "Please enter Email address"

                if (password.isEmpty())
                    signUpPassword.error = "Please enter Password"
            } else {
                val user = User(firstName, lastName, userName, password)
                val intent = Intent()
                intent.putExtra("username", user.getUsername())
                intent.putExtra("password", user.getPassword())
                intent.putExtra("firstname", user.getFirstName())
                intent.putExtra("lastName", user.getLastName())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(Activity.RESULT_CANCELED)
        finish()
    }
}