package com.example.lesson5

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.shopping_activity.*

class ShoppingActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shopping_activity)

//        val intent = getIntent()
        val username = intent.getStringExtra("user")
        welcomeTextView.text = "Welcome $username"

        btnBeauty.setOnClickListener {
            print("here")
            Toast.makeText(this, "You have chosen ${beauty.text.toString()} category of shopping", Toast.LENGTH_LONG).show()
        }
        btnClothing.setOnClickListener{
            Toast.makeText(this, "You have chosen ${cloth.text} category of shopping", Toast.LENGTH_LONG).show()
        }
        btnElectronics.setOnClickListener{
            Toast.makeText(this, "You have chosen ${elect.text} category of shopping", Toast.LENGTH_LONG).show()
        }
        btnFood.setOnClickListener{
            Toast.makeText(this, "You have chosen ${food.text} category of shopping", Toast.LENGTH_LONG).show()
        }
    }


}