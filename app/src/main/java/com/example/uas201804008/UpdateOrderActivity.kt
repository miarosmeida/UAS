package com.example.uas201804008

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class UpdateOrderActivity : AppCompatActivity() {
    lateinit var userDBHelper: DBHelper
    lateinit var inputid: EditText
    lateinit var inputidproduk: EditText
    lateinit var inputnamacust: EditText
    lateinit var inputalamat: EditText
    lateinit var inputjumlah: EditText
    lateinit var inputtotal: EditText
    lateinit var idg: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_order)
        inputid = findViewById(R.id.input_idu)
        inputidproduk = findViewById(R.id.input_idproduku)
        inputnamacust = findViewById(R.id.input_namacustu)
        inputalamat = findViewById(R.id.input_alamatu)
        inputjumlah = findViewById(R.id.input_jumlahu)
        inputtotal = findViewById(R.id.input_totalu)
        userDBHelper = DBHelper(this)
        val bundle =  intent.extras
        if (bundle != null){
            idg = bundle.getString("idk").toString()
            inputid.setText(bundle.getString("idk"))
            inputidproduk.setText(bundle.getString("idprodukk"))
            inputnamacust.setText(bundle.getString("namacustk"))
            inputalamat.setText(bundle.getString("alamatk"))
            inputjumlah.setText(bundle.getString("jumlahk"))
            inputtotal.setText(bundle.getString("totalk"))
        }
        userDBHelper = DBHelper(this)
    }
    fun cancelDataOrder(vorder: View){
        val exit = Intent(this, RvDbOrderActivity::class.java)
        startActivity(exit)
    }
    fun updateDataOrder(vorder: View){
        var idprodukin = inputidproduk.text.toString()
        var namacustin = inputnamacust.text.toString()
        var alamatin = inputalamat.text.toString()
        var jumlahin = inputjumlah.text.toString()
        var totalin = inputtotal.text.toString()
        var idin = idg
        userDBHelper.updateDataOrder(idin, idprodukin, namacustin, alamatin, jumlahin, totalin)
        val exit = Intent(this, RvDbOrderActivity::class.java)
        startActivity(exit)
    }
}