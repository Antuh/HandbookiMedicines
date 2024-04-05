package com.example.handbookimedicines

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MedicineDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "medicines.db"
        private const val TABLE_MEDICINES = "medicines"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_IMAGE_URL = "image_url"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = ("CREATE TABLE $TABLE_MEDICINES ("
                + "$COLUMN_ID INTEGER PRIMARY KEY,"
                + "$COLUMN_NAME TEXT,"
                + "$COLUMN_DESCRIPTION TEXT,"
                + "$COLUMN_IMAGE_URL TEXT"
                + ")")
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_MEDICINES")
        onCreate(db)
    }

    fun insertMedicine(name: String, description: String, imageUrl: String) {
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_DESCRIPTION, description)
            put(COLUMN_IMAGE_URL, imageUrl)
        }

        val db = writableDatabase
        db.insert(TABLE_MEDICINES, null, values)
        db.close()
    }

    fun getAllMedicines(): List<Medicine> {
        val medicineList = mutableListOf<Medicine>()
        val selectQuery = "SELECT * FROM $TABLE_MEDICINES"
        val db = readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                val description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
                val imageUrl = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_URL))
                val medicine = Medicine(id, name, description, imageUrl)
                medicineList.add(medicine)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return medicineList
    }
}

