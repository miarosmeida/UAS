package com.example.uas201804008

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class RvDbProductActivity : AppCompatActivity() {
    private lateinit var rv_tampilanku: RecyclerView
    lateinit var userDBHelper: DBHelperProduct
    private var list: ArrayList<DBModelProduct> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_db_product)
        rv_tampilanku = findViewById(R.id.rv_tampilkan)
        rv_tampilanku.setHasFixedSize(true)
        userDBHelper = DBHelperProduct(this)
        list.addAll(userDBHelper.fullData())
        rv_tampilanku.layoutManager = LinearLayoutManager(this)
        var cardData = DBAdapterProduct(list)
        rv_tampilanku.adapter = cardData
    }
}
