package com.example.uas201804008

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DBAdapterOrder (private val listorder: ArrayList<DBModelOrder>): RecyclerView.Adapter<DBAdapterOrder.CardViewHolder>() {
    inner class CardViewHolder(itemV: View): RecyclerView.ViewHolder(itemV) {
        var tvidku: TextView = itemV.findViewById(R.id.tv_idku)
        var tvidprodku: TextView = itemV.findViewById(R.id.tv_idproduku)
        var tvnamacustku: TextView = itemV.findViewById(R.id.tv_namacustku)
        var tvalamatku: TextView = itemV.findViewById(R.id.tv_alamatku)
        var tvjumlahku: TextView = itemV.findViewById(R.id.tv_jumlahku)
        var tvtotalku: TextView = itemV.findViewById(R.id.tv_totalku)
        var btnDeleteOrder: Button = itemV.findViewById(R.id.btndeleteorder)
        var btnUpdateOrder: Button = itemV.findViewById(R.id.btnupdateorder)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_order, parent, false)
        return CardViewHolder(view)
    }
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val dataku = listorder[position]
        holder.tvidku.text = dataku.id
        holder.tvidprodku.text = dataku.idproduk
        holder.tvnamacustku.text = dataku.namacust
        holder.tvalamatku.text = dataku.alamat
        holder.tvjumlahku.text = dataku.jumlah
        holder.tvtotalku.text = dataku.total

        holder.btnDeleteOrder.setOnClickListener {
            var adapterDBHelper: DBHelper
            adapterDBHelper = DBHelper(holder.itemView.context)
            adapterDBHelper.deleteDataOrder(dataku.id)
            listorder.removeAt(position)
            notifyDataSetChanged()
        }
        holder.btnUpdateOrder.setOnClickListener {
            val pindahUp = Intent(holder.itemView.context, UpdateOrderActivity::class.java)
            val bundle = Bundle()
            bundle.putString("idk", dataku.id)
            bundle.putString("idprodukk", dataku.idproduk)
            bundle.putString("namacustk", dataku.namacust)
            bundle.putString("alamatk", dataku.alamat)
            bundle.putString("jumlahk", dataku.jumlah)
            bundle.putString("totalk", dataku.total)
            pindahUp.putExtras(bundle)
            holder.itemView.context.startActivity(pindahUp)
        }
    }

    override fun getItemCount(): Int {
        return listorder.size
    }
}
