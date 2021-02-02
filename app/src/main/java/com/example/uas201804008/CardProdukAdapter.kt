package com.example.uas201804008

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardProdukAdapter(val item:Array<Int>, val nama:Array<String>, val harga:Array<String>, val stok:Array<String>) :RecyclerView.Adapter<CardProdukAdapter.CardViewHolder>(){
    class CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindValue(image: Int, nm: String, hrga: String, stok: String) {
            itemView.findViewById<ImageView>(R.id.imageView).setImageResource(image)
            itemView.findViewById<TextView>(R.id.text1).text = nm
            itemView.findViewById<TextView>(R.id.text2).text = hrga
            itemView.findViewById<TextView>(R.id.text3).text = stok
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CardViewHolder(view)
    }
    override fun getItemCount(): Int {
        return item.size
    }
    override fun onBindViewHolder(holder: CardProdukAdapter.CardViewHolder, position: Int) {
        holder.bindValue(item[position], nama[position], harga[position], stok[position] )

    }

}