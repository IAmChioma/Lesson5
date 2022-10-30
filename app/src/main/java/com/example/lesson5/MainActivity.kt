package com.example.lesson5

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.signup_layout.*
import kotlinx.android.synthetic.main.signup_layout.view.*

class MainActivity : AppCompatActivity() {
    private var users : ArrayList<User> = arrayListOf<User>(
        User("Alan", lastname = "Stamm", username = "Stamm@gmail.com", password = "111"),
        User("Renee", lastname = "Hahn", username = "Hahn@gmail.com", password = "222"),
        User("Kane", lastname = "Zulauf", username = "Kane@gmail.com", password = "333"),
        User("Cary", lastname = "Towne", username = "Cary@gmail.com", password = "444"),
        User("Ben", lastname = "Pfeffer", username = "Pfeffer@gmail.com", password = "555")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        signIn.setOnClickListener() {
            signUserIn()
        }


        forgotPassword.setOnClickListener() {
            sendUserEmail()
        }

        var resultContracts = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val user = User(it.data?.getStringExtra("firstname").toString(),it.data?.getStringExtra("lastname").toString(),it.data?.getStringExtra("username").toString(),it.data?.getStringExtra("password").toString())
                user.let {
                    if (userIsFound(user)) {
                        Toast.makeText(this, "Email address already exists", Toast.LENGTH_LONG).show()
                    } else {
                        users.add(user)
                        Toast.makeText(this, "Account created successfully.", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }


        create?.setOnClickListener() {
            val intent = Intent(this, SignUpActivity::class.java)
            resultContracts.launch(intent)
        }

    }

//    fun click(view:View){
//        signIn.setOnClickListener() {
//            signUserIn()
//        }
//    }
    private fun userIsFound(user: User) = users.find { it.getUsername() == user.getUsername() } != null

    private fun signUserIn() {
        val email: String = email?.text.toString().trim()
        val pwd: String = password?.text.toString().trim()


        val user = users.find { u -> u.getUsername() == email && u.getPassword() == pwd }
        if (user == null) {
            var message = "Invalid Email address or password"
            if (email.isEmpty()) message = "Please enter Email address"
            if (pwd.isEmpty()) message = "Please enter Password"
            print(message)

            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
        else {
            val intent = Intent(this, ShoppingActivity::class.java)
            intent.putExtra("user", user.getUsername())
            startActivity(intent)
            password.setText("")
        }
    }

    private fun sendUserEmail() {
        val recipient = email.text.toString().trim()
        if (recipient.isNotEmpty()) {
            val pwd: String = users.find {
                it.getUsername() == recipient
            }?.getPassword() ?: ""

            val subject = "Forgot Password"
            var message = "Email address does not exist"
            if (pwd.isNotEmpty()) message = "Your password is $pwd"

            val mIntent = Intent(Intent.ACTION_SEND)
            mIntent.data = Uri.parse("mailto:")
            mIntent.type = "text/plain"
            mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
            mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
            mIntent.putExtra(Intent.EXTRA_TEXT, message)
            startActivity(Intent.createChooser(mIntent, "Choose an application to send the Email"))
        } else {
            Toast.makeText(this, "Please enter your Email address", Toast.LENGTH_LONG).show()
        }
    }
    }
