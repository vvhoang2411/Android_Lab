// DBHelper.kt
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.contactsapp.Contact

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "contacts.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_CONTACTS = "contacts"
        private const val COL_ID = "id"
        private const val COL_NAME = "name"
        private const val COL_PHONE = "phone"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_CONTACTS (" +
                "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_NAME TEXT NOT NULL, " +
                "$COL_PHONE TEXT NOT NULL)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        onCreate(db)
    }

    // Thêm 1 contact, trả về id (hoặc -1 nếu lỗi)
    fun addContact(name: String, phone: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_NAME, name)
            put(COL_PHONE, phone)
        }
        val id = db.insert(TABLE_CONTACTS, null, values)
        db.close()
        return id
    }

    // Lấy tất cả contact
    fun getAllContacts(): List<Contact> {
        val contacts = mutableListOf<Contact>()
        val db = readableDatabase
        val cursor = db.query(
            TABLE_CONTACTS,
            arrayOf(COL_ID, COL_NAME, COL_PHONE),
            null, null, null, null,
            "$COL_ID DESC"
        )

        cursor.use {
            if (it.moveToFirst()) {
                do {
                    val id = it.getLong(it.getColumnIndexOrThrow(COL_ID))
                    val name = it.getString(it.getColumnIndexOrThrow(COL_NAME))
                    val phone = it.getString(it.getColumnIndexOrThrow(COL_PHONE))
                    contacts.add(Contact(id, name, phone))
                } while (it.moveToNext())
            }
        }
        db.close()
        return contacts
    }
}
