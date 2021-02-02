package com.example.uas201804008

import android.provider.BaseColumns

object DBInfoProduct {
    class UserInput: BaseColumns {
        companion object {

            val TABLE_NAME = "product"
            val COL_IDP = "idp"
            val COL_NAMA = "nama"
            val COL_HARGA = "harga"
            val COL_WARNA = "warna"
            val COL_STOK= "stok"

        }
    }
}