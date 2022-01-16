package com.example.myguests.service

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.myguests.constants.DataBaseConstants
import com.example.myguests.model.GuestModel
import java.util.ArrayList

class GuestRepository private constructor(context: Context) {

    private var _guestDataBaseHelper: GuestDataBaseHelper = GuestDataBaseHelper(context)

    fun saveGuest(guest: GuestModel): Boolean {

        return try {
            // get db to write information
            val writableDatabase = _guestDataBaseHelper.writableDatabase

            // variable with instance class
            val values = ContentValues()
            // insert the comuns values into 'values'
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            writableDatabase.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)
            true
        } catch (e: Exception) {
            false
        }

    }

    fun updateGuest(guest: GuestModel): Boolean {

        return try {
            // get db to write information
            val db = _guestDataBaseHelper.writableDatabase

            // variable with instance class
            val values = ContentValues()
            // insert the comuns values into 'values'
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            val columnSelection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val argsToSelection = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, null, columnSelection, argsToSelection)
            true
        } catch (e: Exception) {
            false
        }

    }

    fun delete(guest: GuestModel): Boolean {

        return try {
            // get db to write information
            val db = _guestDataBaseHelper.writableDatabase

            // criteria for removal
            val columnSelection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val argsToSelection = arrayOf(guest.id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, columnSelection, argsToSelection)
            true
        } catch (e: Exception) {
            false
        }

    }

    @SuppressLint("Range")
    fun getOneGuest(id: Int): GuestModel? {

        var guest: GuestModel? = null

        return try {
            val readableDatabase = _guestDataBaseHelper.readableDatabase

            val columnsToReturn = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val filterSelection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val argsToSelection = arrayOf(id.toString())

            val cursor = readableDatabase.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                columnsToReturn,
                filterSelection,
                argsToSelection,
                null,
                null,
                null
            )

            if (cursorIsNotNullOrEmpty(cursor)) {
                cursor.moveToFirst()

                val name =
                    cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                val presence =
                    (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                guest = GuestModel(id, name, presence)
            }

            cursor?.close()
            guest
        } catch (e: Exception) {
            guest
        }

    }

    @SuppressLint("Range")
    fun getAllGuests(): List<GuestModel> {

        val list: MutableList<GuestModel> = ArrayList()

        return try {

            val readableDatabase = _guestDataBaseHelper.readableDatabase

            val columnsToReturn = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val cursor = readableDatabase.query(
                DataBaseConstants.GUEST.TABLE_NAME,
                columnsToReturn,
                null,
                null,
                null,
                null,
                null
            )

            if (cursorIsNotNullOrEmpty(cursor)) {

                while (cursor.moveToNext()) {

                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }
            }

            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }

    }

    @SuppressLint("Range")
    fun getAbsentGuests(): List<GuestModel> {

        val list: MutableList<GuestModel> = ArrayList()

        return try {

            val readableDatabase = _guestDataBaseHelper.readableDatabase

            val cursor = readableDatabase.rawQuery("SELECT * FROM Guest WHERE presence = 0", null)

            if (cursorIsNotNullOrEmpty(cursor)) {

                while (cursor.moveToNext()) {

                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }
            }

            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
    }

    @SuppressLint("Range")
    fun getPresentGuests(): List<GuestModel> {

        val list: MutableList<GuestModel> = ArrayList()

        return try {

            val readableDatabase = _guestDataBaseHelper.readableDatabase

            val cursor = readableDatabase.rawQuery("SELECT * FROM Guest WHERE presence = 1", null)

            if (cursorIsNotNullOrEmpty(cursor)) {

                while (cursor.moveToNext()) {

                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }
            }

            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }

    }

    private fun cursorIsNotNullOrEmpty(cursor: Cursor?): Boolean {
        return cursor != null && cursor.count > 0
    }

    companion object {

        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            // Verify if repository was initialized
            if (!::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }

    }

}
