package com.example.uas201804008

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class OrderActivity : AppCompatActivity() {
    lateinit var orderDBHelper:DBHelper
    lateinit var inputid: EditText
    lateinit var inputidproduk: EditText
    lateinit var inputnamacust: EditText
    lateinit var inputalamat: EditText
    lateinit var inputjumlah: EditText
    lateinit var inputtotal: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        inputid = findViewById(R.id.input_id)
        inputidproduk = findViewById(R.id.input_idproduk)
        inputnamacust = findViewById(R.id.input_namacust)
        inputalamat = findViewById(R.id.input_alamat)
        inputjumlah = findViewById(R.id.input_jumlah)
        inputtotal = findViewById(R.id.input_total)
        orderDBHelper = DBHelper(this)
        var btnBacko : Button = findViewById(R.id.btn_backo)
        var btnSubmit :Button =findViewById(R.id.btn_submitorder)
        btnBacko.setOnClickListener {
            var kembali = Intent(this, MainActivity2::class.java)
            startActivity(kembali)
        }
        btnSubmit.setOnClickListener {
            var idin = inputid.text.toString()
            var idprodukin = inputidproduk.text.toString()
            var namacustin = inputnamacust.text.toString()
            var alamatin = inputalamat.text.toString()
            var jumlahin = inputjumlah.text.toString()
            var totalin = inputtotal.text.toString()
            orderDBHelper.insertDataOrder(idin, idprodukin, namacustin, alamatin, jumlahin, totalin)
            inputid.setText("")
            inputidproduk.setText("")
            inputnamacust.setText("")
            inputalamat.setText("")
            inputjumlah.setText("")
            inputtotal.setText("")
        }
    }

    fun showAllOrder(v: View){
        var pindah = Intent(this, RvDbOrderActivity::class.java)
        startActivity(pindah)
    }
}