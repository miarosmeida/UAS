package com.example.uas201804008

import android.provider.BaseColumns

object DBInfoOrder {

    class UserInput: BaseColumns {
        companion object {

            val TABLE_NAME = "order"
            val COL_ID = "idp"
            val COL_IDPROD = "idproduk"
            val COL_NAMA = "namacust"
            val COL_ALAMAT = "alamat"
            val COL_JUMLAH = "jumlah"
            val COL_TOTAL = "total"

        }
    }
}