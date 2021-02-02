package com.example.uas201804008

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DBAdapterProduct(private val listDataku: ArrayList<DBModelProduct>): RecyclerView.Adapter<DBAdapterProduct.CardViewHolder>() {
    inner class CardViewHolder(itemV: View): RecyclerView.ViewHolder(itemV) {
        var tvidpku: TextView = itemV.findViewById(R.id.tv_idpku)
        var tvnamaku: TextView = itemV.findViewById(R.id.tv_namaku)
        var tvhargaku: TextView = itemV.findViewById(R.id.tv_hargaku)
        var tvwarnaku: TextView = itemV.findViewById(R.id.tv_warnaku)
        var tvstokku: TextView = itemV.findViewById(R.id.tv_stokku)
        var btnDelete: Button = itemV.findViewById(R.id.btndelete)
        var btnUpdate: Button = itemV.findViewById(R.id.btnupdate)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_product, parent, false)
        return CardViewHolder(view)
    }
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val dataku = listDataku[position]
        holder.tvidpku.text = dataku.idp
        holder.tvnamaku.text = dataku.nama
        holder.tvhargaku.text = dataku.harga
        holder.tvwarnaku.text = dataku.warna
        holder.tvstokku.text = dataku.stok

        holder.btnDelete.setOnClickListener {
            var adapterDBHelper: DBHelperProduct
            adapterDBHelper = DBHelperProduct(holder.itemView.context)
            adapterDBHelper.deleteData(dataku.idp)
            listDataku.removeAt(position)
            notifyDataSetChanged()
        }
        holder.btnUpdate.setOnClickListener {
            val pindahUp = Intent(holder.itemView.context, UpdateActivity::class.java)
            val bundle = Bundle()
            bundle.putString("idpkk", dataku.idp)
            bundle.putString("namakk", dataku.nama)
            bundle.putString("hargakk", dataku.harga)
            bundle.putString("warnakk", dataku.warna)
            bundle.putString("stokkk", dataku.stok)
            pindahUp.putExtras(bundle)
            holder.itemView.context.startActivity(pindahUp)
        }
    }

    override fun getItemCount(): Int {
        return listDataku.size
    }
}
