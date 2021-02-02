package com.example.uas201804008

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelperProduct (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        val DATABASE_NAME = "Product.db"
        val DATABASE_VERSION = 1
        private val SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DBInfoProduct.UserInput.TABLE_NAME + " (" + DBInfoProduct.UserInput.COL_IDP +
                    " VARCHAR(200) PRIMARY KEY, " + DBInfoProduct.UserInput.COL_NAMA + " TEXT, " +
                    DBInfoProduct.UserInput.COL_HARGA + " VARCHAR(200), " + DBInfoProduct.UserInput.COL_WARNA +
                    " VARCHAR(30), " + DBInfoProduct.UserInput.COL_STOK + " VARCHAR(15) )"
        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBInfoProduct.UserInput.TABLE_NAME
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertData(idpin: String, namain: String, hargain: String, warnain: String, stokin: String): Boolean {
        val db = writableDatabase
        val namatablet = DBInfoProduct.UserInput.TABLE_NAME
        val idpit = DBInfoProduct.UserInput.COL_IDP
        val namat = DBInfoProduct.UserInput.COL_NAMA
        val hargat = DBInfoProduct.UserInput.COL_HARGA
        val warnat = DBInfoProduct.UserInput.COL_WARNA
        val stokt = DBInfoProduct.UserInput.COL_STOK

        var sql = "INSERT INTO "+ namatablet +"("+idpit+", "+namat+", "+hargat+", "+warnat+", "+stokt+") " +
                "VALUES('"+idpin+"', '"+namain+"', '"+hargain+"', '"+warnain+"', '"+stokin+"')"
        db.execSQL(sql)
        return true
    }
    fun fullData():ArrayList<DBModelProduct>{
        //      val users = ArrayList<DBModel>()
        val product = arrayListOf<DBModelProduct>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM "+ DBInfoProduct.UserInput.TABLE_NAME, null)
        }catch (e: SQLException){
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var idpt: String
        var namat: String
        var hargat: String
        var warnat: String
        var stokt: String
        if (cursor!!.moveToFirst()){
            while (cursor.isAfterLast==false){
                idpt = cursor.getString(cursor.getColumnIndex(DBInfoProduct.UserInput.COL_IDP))
                namat = cursor.getString(cursor.getColumnIndex(DBInfoProduct.UserInput.COL_NAMA))
                hargat = cursor.getString(cursor.getColumnIndex(DBInfoProduct.UserInput.COL_HARGA))
                warnat = cursor.getString(cursor.getColumnIndex(DBInfoProduct.UserInput.COL_WARNA))
                stokt = cursor.getString(cursor.getColumnIndex(DBInfoProduct.UserInput.COL_STOK))

                product.add(DBModelProduct(idpt, namat, hargat, warnat, stokt))
                cursor.moveToNext()
            }
        }
        return  product
    }
    fun deleteData(idpin: String){
        val db = writableDatabase
        val namatablet = DBInfoProduct.UserInput.TABLE_NAME
        val idpt = DBInfoProduct.UserInput.COL_IDP
        val sql = "DELETE FROM " +namatablet+ " WHERE " +idpt+"='"+idpin+"'"
        db.execSQL(sql)
    }
    fun updateData(idpinn: String, namainn: String, hargainn: String, warnainn: String, stokinn: String) {
        val db = writableDatabase
        val namatablet = DBInfoProduct.UserInput.TABLE_NAME
        val idptt = DBInfoProduct.UserInput.COL_IDP
        val namatt = DBInfoProduct.UserInput.COL_NAMA
        val hargatt = DBInfoProduct.UserInput.COL_HARGA
        val warnatt = DBInfoProduct.UserInput.COL_WARNA
        val stoktt = DBInfoProduct.UserInput.COL_STOK
        var sql = "UPDATE "+ namatablet + " SET "+
                namatt+"='"+namainn+"', "+hargatt+"='"+hargainn+"', "+warnatt+"='"+warnainn+"', "+stoktt+"='"+stokinn+"' "+
                "WHERE "+idptt+"='"+idpinn+"'"
        db.execSQL(sql)

    }

}