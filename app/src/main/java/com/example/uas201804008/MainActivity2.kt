package com.example.uas201804008

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val btnData: Button = findViewById(R.id.btnData)
        val btnOrder: Button = findViewById(R.id.btn_order)
        val btnLogout: Button = findViewById(R.id.btnLogout)
        val savedLogin = getSharedPreferences("Login", MODE_PRIVATE)
        val editSavedLogin = savedLogin.edit()
        btnLogout.setOnClickListener {
            editSavedLogin.putString("Email", null)
            editSavedLogin.putString("Password", null)
            editSavedLogin.putString("Status", "Off")
            editSavedLogin.commit()
            startActivity(Intent(this, LoginActivity::class.java))
        }
        btnData.setOnClickListener {
            startActivity(Intent(this, ProductActivity::class.java))
        }
        btnOrder.setOnClickListener {
            startActivity(Intent(this, OrderActivity::class.java))
        }
    }
}