// MainActivity.kt
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.contactsapp.Contact
import com.example.contactsapp.R


class MainActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etPhone: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnView: Button
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.etName)
        etPhone = findViewById(R.id.etPhone)
        btnAdd = findViewById(R.id.btnAdd)
        btnView = findViewById(R.id.btnView)

        dbHelper = DBHelper(this)

        btnAdd.setOnClickListener {
            val name = etName.text.toString().trim()
            val phone = etPhone.text.toString().trim()

            if (name.isEmpty()) {
                etName.error = "Nhập tên"
                return@setOnClickListener
            }
            if (phone.isEmpty()) {
                etPhone.error = "Nhập số điện thoại"
                return@setOnClickListener
            }

            val id = dbHelper.addContact(name, phone)
            if (id != -1L) {
                Toast.makeText(this, "Đã thêm liên hệ", Toast.LENGTH_SHORT).show()
                etName.text.clear()
                etPhone.text.clear()
            } else {
                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show()
            }
        }

        btnView.setOnClickListener {
            startActivity(Intent(this, ContactsActivity ::class.java))
        }
    }
}


