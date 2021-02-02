package com.example.uas201804008

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelperOrder (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        val DATABASE_NAME = "Order.db"
        val DATABASE_VERSION = 1
        private val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBInfoOrder.UserInput.TABLE_NAME + " ("+DBInfoOrder.UserInput.COL_ID + " VARCHAR(20) PRIMARY KEY, "+
                    DBInfoOrder.UserInput.COL_IDPROD+" VARCHAR (20), " +DBInfoOrder.UserInput.COL_NAMA+" VARCHAR(200), "+DBInfoOrder.UserInput.COL_ALAMAT+" TEXT, "+
                    DBInfoOrder.UserInput.COL_JUMLAH+" VARCHAR(20), "+DBInfoOrder.UserInput.COL_TOTAL+" VARCHAR(20)) "
        private val SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS" +DBInfoOrder.UserInput.TABLE_NAME
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertData(idin: String, idprodukin: String, namacustin: String, alamatin: String, jumlahin: String, totalin: String): Boolean {
        val db = writableDatabase
        val namatablet = DBInfoOrder.UserInput.TABLE_NAME
        val idt = DBInfoOrder.UserInput.COL_ID
        val idprodukt = DBInfoOrder.UserInput.COL_IDPROD
        val namacustt = DBInfoOrder.UserInput.COL_NAMA
        val alamatt = DBInfoOrder.UserInput.COL_ALAMAT
        val jumlaht = DBInfoOrder.UserInput.COL_JUMLAH
        val totalt = DBInfoOrder.UserInput.COL_TOTAL


        var sql = "INSERT INTO "+ namatablet +"("+idt+", "+idprodukt+", "+namacustt+", "+alamatt+", "+jumlaht+","+totalt+") " +
                "VALUES('"+idin+"', '"+idprodukin+"', '"+namacustin+"', '"+alamatin+"', '"+jumlahin+"', '"+totalin+"')"
        db.execSQL(sql)
        return true
    }
    fun fullDataOrder():ArrayList<DBModelOrder>{
        //      val users = ArrayList<DBModel>()
        val order = arrayListOf<DBModelOrder>()
        val db = writableDatabase
        var cursororder: Cursor? = null
        try {
            cursororder = db.rawQuery("SELECT * FROM "+ DBInfoOrder.UserInput.TABLE_NAME, null)
        }catch (e: SQLException){
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var idt: String
        var idprodukt: String
        var namacustt: String
        var alamatt: String
        var jumlaht: String
        var totalt: String
        if (cursororder!!.moveToFirst()){
            while (cursororder.isAfterLast==false){
                idt = cursororder.getString(cursororder.getColumnIndex(DBInfoOrder.UserInput.COL_ID))
                idprodukt = cursororder.getString(cursororder.getColumnIndex(DBInfoOrder.UserInput.COL_IDPROD))
                namacustt = cursororder.getString(cursororder.getColumnIndex(DBInfoOrder.UserInput.COL_NAMA))
                alamatt = cursororder.getString(cursororder.getColumnIndex(DBInfoOrder.UserInput.COL_ALAMAT))
                jumlaht = cursororder.getString(cursororder.getColumnIndex(DBInfoOrder.UserInput.COL_JUMLAH))
                totalt = cursororder.getString(cursororder.getColumnIndex(DBInfoOrder.UserInput.COL_TOTAL))

                order.add(DBModelOrder(idt, idprodukt, namacustt, alamatt, jumlaht, totalt))
                cursororder.moveToNext()
            }
        }
        return  order
    }
    fun deleteDataOrder(idin: String){
        val db = writableDatabase
        val namatablet = DBInfoOrder.UserInput.TABLE_NAME
        val idt = DBInfoOrder.UserInput.COL_ID
        val sql = "DELETE FROM " + namatablet + " WHERE " + idt +"='"+ idin +"'"
        db.execSQL(sql)
    }
    fun updateDataOrder(idin: String, idprodukin: String, namacustin: String, alamatin: String, jumlahin: String, totalin: String) {
        val db = writableDatabase
        val namatablet = DBInfoOrder.UserInput.TABLE_NAME
        val idt = DBInfoOrder.UserInput.COL_ID
        val idprodukt = DBInfoOrder.UserInput.COL_IDPROD
        val namacustt = DBInfoOrder.UserInput.COL_NAMA
        val alamatt = DBInfoOrder.UserInput.COL_ALAMAT
        val jumlaht = DBInfoOrder.UserInput.COL_JUMLAH
        val totalt = DBInfoOrder.UserInput.COL_TOTAL
        var sql = "UPDATE " + namatablet + " SET " +
                idprodukt+"='"+idprodukin+"', "+ namacustt+"='"+namacustin+"', "+alamatt+"='"+alamatin+
                "', "+jumlaht+"='"+jumlahin+"', "+totalt+"='"+totalin+"' "+
                "WHERE "+idt+"='"+idin+"'"
        db.execSQL(sql)

    }

}