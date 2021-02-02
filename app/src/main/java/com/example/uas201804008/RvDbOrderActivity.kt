package com.example.uas201804008

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class RvDbOrderActivity : AppCompatActivity() {
    private lateinit var rv_tampilanku: RecyclerView
    lateinit var userDBHelper: DBHelper
    private var list: ArrayList<DBModelOrder> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_db_order)
        rv_tampilanku = findViewById(R.id.rv_tampilkanorder)
        rv_tampilanku.setHasFixedSize(true)
        userDBHelper = DBHelper(this)
        list.addAll(userDBHelper.fullDataOrder())
        rv_tampilanku.layoutManager = LinearLayoutManager(this)
        var cardData = DBAdapterOrder(list)
        rv_tampilanku.adapter = cardData
    }
}
