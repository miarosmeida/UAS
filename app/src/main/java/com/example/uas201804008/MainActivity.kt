package com.example.uas201804008

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val item = arrayOf(R.drawable.bajubiru, R.drawable.set, R.drawable.moka,
        R.drawable.white, R.drawable.coksu, R.drawable.bfashion, R.drawable.bajuhtm,
        R.drawable.celana, R.drawable.bajunavy)
    private val nama = arrayOf("Emikowa - Daily outer", "Stelan Scuba ", "Classy - Rajut", "Classy - Rajut", "Classy - Rajut",
        "Classy - Rajut", "vSamantha - Stelan Piyama", "Celana Scuba", "vSamantha - Stelan Tie Dye")
    private val harga = arrayOf("Harga : Rp. 99.999-,", "Harga : Rp. 250.000-,", "Harga : Rp. 89.999-,", "Harga : Rp. 89.999-,", "Harga : Rp. 89.999-,",
        "Harga : Rp. 89.999-,", "Harga : Rp. 180.099-,", "Harga : Rp. 150.999-,","Harga : Rp. 180.999-,"
    )
    private val stok = arrayOf(
        "Stok : 5", "Stok : 3", "Stok : 2", "Stok : 15", "Stok : 8", "Stok : 3", "Stok : 1","Stok : 4","Stok : 1"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCek: Button = findViewById(R.id.btn_cek)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_prdk)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CardProdukAdapter(item, nama, harga, stok)

        btnCek.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }
    }


}
