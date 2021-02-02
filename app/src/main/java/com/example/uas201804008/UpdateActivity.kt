package com.example.uas201804008

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class UpdateActivity : AppCompatActivity() {
    lateinit var userDBHelper: DBHelperProduct
    lateinit var inputIdp: EditText
    lateinit var inputNama: EditText
    lateinit var inputHarga: EditText
    lateinit var inputWarna: EditText
    lateinit var inputStok: EditText
    lateinit var idpg: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        inputIdp = findViewById(R.id.input_idpu)
        inputNama = findViewById(R.id.input_namau)
        inputHarga = findViewById(R.id.input_hargau)
        inputWarna = findViewById(R.id.input_warnau)
        inputStok = findViewById(R.id.input_stoku)
        userDBHelper = DBHelperProduct(this)
        val bundle =  intent.extras
        if (bundle != null){
            idpg = bundle.getString("idpkk").toString()
            inputIdp.setText(bundle.getString("idpkk"))
            inputNama.setText(bundle.getString("namakk"))
            inputHarga.setText(bundle.getString("hargakk"))
            inputWarna.setText(bundle.getString("warnakk"))
            inputStok.setText(bundle.getString("stokkk"))
        }
        userDBHelper = DBHelperProduct(this)
    }
    fun cancelData(v: View){
        val exit = Intent(this, RvDbProductActivity::class.java)
        startActivity(exit)
    }
    fun updateData(v: View){
        var namain = inputNama.text.toString()
        var hargain = inputHarga.text.toString()
        var warnain = inputWarna.text.toString()
        var stokin = inputStok.text.toString()
        var idpin = idpg
        userDBHelper.updateData(namain, hargain, warnain, stokin, idpin)
        val exit = Intent(this, RvDbProductActivity::class.java)
        startActivity(exit)
    }
}