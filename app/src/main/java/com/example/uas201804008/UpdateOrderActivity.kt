package com.example.uas201804008

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class UpdateOrderActivity : AppCompatActivity() {
    lateinit var userDBHelper: DBHelper
    lateinit var inputidd: EditText
    lateinit var inputidprodukk: EditText
    lateinit var inputnamacustt: EditText
    lateinit var inputalamatt: EditText
    lateinit var inputjumlahh: EditText
    lateinit var inputtotall: EditText
    lateinit var idgg: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_order)
        inputidd = findViewById(R.id.input_idu)
        inputidprodukk = findViewById(R.id.input_idproduku)
        inputnamacustt = findViewById(R.id.input_namacustu)
        inputalamatt = findViewById(R.id.input_alamatu)
        inputjumlahh = findViewById(R.id.input_jumlahu)
        inputtotall = findViewById(R.id.input_totalu)
        userDBHelper = DBHelper(this)
        val bundle =  intent.extras
        if (bundle != null){
            idgg = bundle.getString("idk").toString()
            inputidd.setText(bundle.getString("idk"))
            inputidprodukk.setText(bundle.getString("idprodukk"))
            inputnamacustt.setText(bundle.getString("namacustk"))
            inputalamatt.setText(bundle.getString("alamatk"))
            inputjumlahh.setText(bundle.getString("jumlahk"))
            inputtotall.setText(bundle.getString("totalk"))
        }
        userDBHelper = DBHelper(this)
    }
    fun Menuo(v: View){
        val exit = Intent(this, MainActivity2::class.java)
        startActivity(exit)
    }
    fun cancelDataOrder(vorder: View){
        val exit = Intent(this, RvDbOrderActivity::class.java)
        startActivity(exit)
    }
    fun updateDataOrder(vorder: View){
        var idprodukinn = inputidprodukk.text.toString()
        var namacustinn = inputnamacustt.text.toString()
        var alamatinn = inputalamatt.text.toString()
        var jumlahinn = inputjumlahh.text.toString()
        var totalinn = inputtotall.text.toString()
        var idinn = idgg
        userDBHelper.updateDataOrder(idinn, idprodukinn, namacustinn, alamatinn, jumlahinn, totalinn)
        val exit = Intent(this, RvDbOrderActivity::class.java)
        startActivity(exit)
    }
}