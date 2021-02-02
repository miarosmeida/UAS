package com.example.uas201804008

import android.provider.BaseColumns

object DBInfo {

    class UserTable : BaseColumns {
        companion object {
            val TABLE_NAME = "user"
            val COL_EMAIL = "email"
            val COL_PASS = "pass"
            val COL_FULLNAME = "fullname"
            val COL_ALAMAT = "alamat"
            val COL_JK = "jeniskelamin"
            val COL_JUMLAH = "jumlah"
        }
    }

    class OrderInput : BaseColumns {
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