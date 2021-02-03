package com.example.uas201804008

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        val DATABASE_NAME = "myaps.db"
        val DATABASE_VERSION = 1
        private val SQL_CREATE_USER = "CREATE TABLE " + DBInfo.UserTable.TABLE_NAME + "("+DBInfo.UserTable.COL_EMAIL+" VARCHAR(200) PRIMARY KEY, " + DBInfo.UserTable.COL_PASS + " TEXT, " + DBInfo.UserTable.COL_FULLNAME + " TEXT, " + DBInfo.UserTable.COL_JK + " VARCHAR(200), " + DBInfo.UserTable.COL_ALAMAT + " TEXT)"
//        private val SQL_CREATE_ORDER =  "CREATE TABLE " + DBInfo.OrderInput.TABLE_NAME + " ("+DBInfo.OrderInput.COL_ID + " VARCHAR(20) PRIMARY KEY, "+ DBInfo.OrderInput.COL_IDPROD+" VARCHAR (20), " +DBInfo.OrderInput.COL_NAMA+" VARCHAR(200), "+DBInfo.OrderInput.COL_ALAMAT+" TEXT, "+ DBInfo.OrderInput.COL_JUMLAH+" VARCHAR(20), "+DBInfo.OrderInput.COL_TOTAL+" VARCHAR(20)) "
        private val SQL_CREATE_OR = "CREATE TABLE "+DBInfo.OrderInput.TABLE_NAME+" ("+DBInfo.OrderInput.COL_ID+" VARCHAR(200) PRIMARY KEY, "+DBInfo.OrderInput.COL_NAMA+" TEXT, "+DBInfo.OrderInput.COL_IDPROD+" VARCHAR(200), "+DBInfo.OrderInput.COL_ALAMAT+" TEXT, "+DBInfo.OrderInput.COL_JUMLAH+" TEXT, "+DBInfo.OrderInput.COL_TOTAL+" TEXT)"
        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBInfo.UserTable.TABLE_NAME
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_USER)
        db?.execSQL(SQL_CREATE_OR)
//        db?.execSQL(SQL_CREATE_ORDER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    @Throws(SQLiteConstraintException::class)
    fun RegisterUser(emailin: String, passin:String, fullnamein: String, jenkalin: String, alamatin: String) {
        val db = writableDatabase
        val namatable = DBInfo.UserTable.TABLE_NAME
        val emailt = DBInfo.UserTable.COL_EMAIL
        val passt = DBInfo.UserTable.COL_PASS
        val fullnamet = DBInfo.UserTable.COL_FULLNAME
        val jkt = DBInfo.UserTable.COL_JK
        val alamatt = DBInfo.UserTable.COL_ALAMAT
        var sql = "INSERT INTO " + namatable + " (" + emailt + ", " + passt + ", " + fullnamet + ", " + jkt + ", " + alamatt + ") VALUES('" + emailin + "', '" + passin + "', '" + fullnamein + "', '" + jenkalin + "', '" + alamatin + "')"
        db.execSQL(sql)
    }
    fun cekUser(emailin: String): String {
        val db = writableDatabase
        var cursor: Cursor? = null
        var jumlah = ""
        try {
            cursor = db.rawQuery("SELECT COUNT("+ DBInfo.UserTable.COL_EMAIL +") as jumlah FROM "+ DBInfo.UserTable.TABLE_NAME + " WHERE " + DBInfo.UserTable.COL_EMAIL + "=='" + emailin +"'" , null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_USER)
            return "Tabel Dibuat"
        }
        if (cursor!!.moveToFirst()){
            jumlah = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_JUMLAH))
        }
        return jumlah
    }
    fun cekLogin(emailin: String, passin: String): String {
        val db = writableDatabase
        var cursor: Cursor? = null
        var jumlah = ""
        try {
            cursor = db.rawQuery("SELECT COUNT("+ DBInfo.UserTable.COL_EMAIL +") as jumlah FROM "+ DBInfo.UserTable.TABLE_NAME + " WHERE " + DBInfo.UserTable.COL_EMAIL + "=='" + emailin +"' AND " + DBInfo.UserTable.COL_PASS + "=='" + passin + "'" , null)
        } catch (e: android.database.SQLException) {
            db.execSQL(SQL_CREATE_USER)
            return "Tabel Dibuat"
        }
        if (cursor!!.moveToFirst()){
            jumlah = cursor.getString(cursor.getColumnIndex(DBInfo.UserTable.COL_JUMLAH))
        }
        return jumlah
    }

    @Throws(SQLiteConstraintException::class)
    fun insertDataOrder(idin: String, idprodukin: String, namacustin: String, alamatin: String, jumlahin: String, totalin: String){
        val dbb = writableDatabase
        val namatablet = DBInfo.OrderInput.TABLE_NAME
        val idt = DBInfo.OrderInput.COL_ID
        val idprodukt = DBInfo.OrderInput.COL_IDPROD
        val namacustt = DBInfo.OrderInput.COL_NAMA
        val alamatt = DBInfo.OrderInput.COL_ALAMAT
        val jumlaht = DBInfo.OrderInput.COL_JUMLAH
        val totalt = DBInfo.OrderInput.COL_TOTAL
        var sql = "INSERT INTO "+namatablet+"("+idt+", "+idprodukt+", "+namacustt+", "+alamatt+", "+jumlaht+", "+totalt+") VALUES('"+idin+"', '"+idprodukin+"', '"+namacustin+ "', '"+alamatin+"', '"+jumlahin+"', '"+totalin+"')"
        dbb.execSQL(sql)
    }
    fun fullDataOrder():ArrayList<DBModelOrder>{
        val order = arrayListOf<DBModelOrder>()
        val dbb = writableDatabase
        var cursororder: Cursor? = null
        try {
            cursororder = dbb.rawQuery("SELECT * FROM "+ DBInfo.OrderInput.TABLE_NAME, null)
        }catch (e: android.database.SQLException){
            dbb.execSQL(SQL_CREATE_OR)
            return ArrayList()
        }

        var idtt: String
        var idproduktt: String
        var namacusttt: String
        var alamattt: String
        var jumlahtt: String
        var totaltt: String
        if (cursororder!!.moveToFirst()){
            while (cursororder.isAfterLast==false){
                idtt = cursororder.getString(cursororder.getColumnIndex(DBInfo.OrderInput.COL_ID))
                idproduktt = cursororder.getString(cursororder.getColumnIndex(DBInfo.OrderInput.COL_IDPROD))
                namacusttt = cursororder.getString(cursororder.getColumnIndex(DBInfo.OrderInput.COL_NAMA))
                alamattt = cursororder.getString(cursororder.getColumnIndex(DBInfo.OrderInput.COL_ALAMAT))
                jumlahtt = cursororder.getString(cursororder.getColumnIndex(DBInfo.OrderInput.COL_JUMLAH))
                totaltt = cursororder.getString(cursororder.getColumnIndex(DBInfo.OrderInput.COL_TOTAL))

                order.add(DBModelOrder(idtt, idproduktt, namacusttt, alamattt, jumlahtt, totaltt))
                cursororder.moveToNext()
            }
        }
        return  order
    }
    fun deleteDataOrder(idin: String){
        val dbb = writableDatabase
        val namatablet = DBInfo.OrderInput.TABLE_NAME
        val idt = DBInfo.OrderInput.COL_ID
        val sql = "DELETE FROM " + namatablet + " WHERE " + idt +"='"+ idin +"'"
        dbb.execSQL(sql)
    }
    fun updateDataOrder(idin: String, idprodukin: String, namacustin: String, alamatin: String, jumlahin: String, totalin: String) {
        val dbb = writableDatabase
        val namatablet = DBInfo.OrderInput.TABLE_NAME
        val idt = DBInfo.OrderInput.COL_ID
        val idprodukt = DBInfo.OrderInput.COL_IDPROD
        val namacustt = DBInfo.OrderInput.COL_NAMA
        val alamatt = DBInfo.OrderInput.COL_ALAMAT
        val jumlaht = DBInfo.OrderInput.COL_JUMLAH
        val totalt = DBInfo.OrderInput.COL_TOTAL
        var sql = "UPDATE " + namatablet + " SET " +
                idprodukt + "='" + idprodukin + "', " + namacustt + "='" + namacustin + "', " + alamatt + "='" + alamatin +
                "', " + jumlaht + "='" + jumlahin + "', " + totalt + "='" + totalin + "' " +
                "WHERE " + idt + "='" + idin + "'"
        dbb.execSQL(sql)
    }

    }



