// ContactsActivity.kt
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.contactsapp.R

class ContactsActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        listView = findViewById(R.id.listView)
        dbHelper = DBHelper(this)

        loadContacts()
    }

    private fun loadContacts() {
        val contacts = dbHelper.getAllContacts()
        // Dùng ArrayAdapter hiển thị "Tên - Số"
        val displayList = contacts.map { "${it.name} - ${it.phone}" }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, displayList)
        listView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        // reload khi quay lại màn hình (nếu đã thêm contact mới)
        loadContacts()
    }
}
