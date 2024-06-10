package com.example.kashmirmarsiyaapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "marsiya.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "marsiyas"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_LOGO = "logo"
        const val COLUMN_HEADING = "heading"
        const val COLUMN_CONTENT = "content"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_TITLE TEXT, " +
                "$COLUMN_LOGO INTEGER, " +
                "$COLUMN_HEADING TEXT, " +
                "$COLUMN_CONTENT TEXT)"
                )
        try {
            db?.execSQL(createTable)
            Log.d("DatabaseHelper", "Table created: $TABLE_NAME")
        } catch (e: Exception) {
            Log.e("DatabaseHelper", "Error creating table: ${e.message}")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun getAllMarsiyas(): List<LanguageData> {
        val marsiyaList = ArrayList<LanguageData>()
        val db = this.readableDatabase
        if (db != null) {
            val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    try {
                        do {
                            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
                            val logo = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_LOGO))
                            val heading = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HEADING))
                            val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))
                            marsiyaList.add(LanguageData(id, title, logo, heading, content))
                        } while (cursor.moveToNext())
                    } catch (e: Exception) {
                        Log.e("DatabaseHelper", "Error reading from cursor: ${e.message}")
                    }
                }
                cursor.close()
            } else {
                Log.e("DatabaseHelper", "Cursor is null")
            }
        } else {
            Log.e("DatabaseHelper", "Database is null")
        }
        return marsiyaList
    }

    fun getMarsiyaContent(title: String): String? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT $COLUMN_CONTENT, $COLUMN_HEADING FROM $TABLE_NAME WHERE $COLUMN_TITLE=?", arrayOf(title))
        return if (cursor.moveToFirst()) {
            val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))
            cursor.close()
            content
        } else {
            cursor.close()
            null
        }
    }

    fun addMarsiya(title: String, logo: Int, heading: String, content: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, title)
            put(COLUMN_LOGO, logo)
            put(COLUMN_HEADING, heading)
            put(COLUMN_CONTENT, content)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updateMarsiya(oldTitle: String, newTitle: String, newHeading: String, newContent: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, newTitle)
            put(COLUMN_HEADING, newHeading)
            put(COLUMN_CONTENT, newContent)
        }
        val result = db.update(TABLE_NAME, values, "$COLUMN_TITLE=?", arrayOf(oldTitle))
        db.close()
        return result > 0
    }
}
