package com.example.uas201804008

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class ProductActivity : AppCompatActivity() {
    lateinit var userDBHelper:DBHelperProduct
    lateinit var inputIdp: EditText
    lateinit var inputNama: EditText
    lateinit var inputHarga: EditText
    lateinit var inputWarna: EditText
    lateinit var inputStok: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        inputIdp = findViewById(R.id.input_idp)
        inputNama = findViewById(R.id.input_nama)
        inputHarga = findViewById(R.id.input_harga)
        inputWarna = findViewById(R.id.input_warna)
        inputStok = findViewById(R.id.input_stok)
        userDBHelper = DBHelperProduct(this)
    }
   fun Back(v:View){
       var kembali = Intent(this, MainActivity2::class.java)
       startActivity(kembali)
   }
    fun addData(v: View){
        var idpin = inputIdp.text.toString()
        var namain = inputNama.text.toString()
        var hargain = inputHarga.text.toString()
        var warnain = inputWarna.text.toString()
        var stokin = inputStok.text.toString()
        userDBHelper.insertData(idpin, namain, hargain, warnain, stokin)
        inputIdp.setText("")
        inputNama.setText("")
        inputHarga.setText("")
        inputWarna.setText("")
        inputStok.setText("")
    }
    fun showAll(v: View){
//        var pindah = Intent(this, MainActivity2::class.java)
        var pindah = Intent(this, RvDbProductActivity::class.java)
        startActivity(pindah)
    }
}