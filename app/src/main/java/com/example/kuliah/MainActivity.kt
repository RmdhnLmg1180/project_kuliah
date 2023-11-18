package com.example.kuliah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

inline fun <reified T : View> AppCompatActivity.findView(id: Int): T = findViewById(id)
class MainActivity : AppCompatActivity() {

    private lateinit var userId: EditText
    private lateinit var password: EditText
    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var register: Button

    companion object {
        private const val USER_REGISTERED_MESSAGE = "User Registered!"
        private const val FILL_ALL_FIELDS_MESSAGE = "Fill all fields!"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        userId = findView(R.id.userId)
        password = findView(R.id.password)
        name = findView(R.id.name)
        email = findView(R.id.email)
        register = findView(R.id.register)

//        userId = findViewById(R.id.userId)
//        password = findViewById(R.id.password)
//        name = findViewById(R.id.name)
//        email = findViewById(R.id.email)
//        register = findViewById(R.id.register)

        register.setOnClickListener {
            // Membuat objek UserEntity dan mengisi properti
            var userEntity = UserEntity()
            userEntity.userId = userId.text.toString()
            userEntity.password = password.text.toString()
            userEntity.name = name.text.toString()
            userEntity.email = email.text.toString() // Mengisi properti email

            if (validateInput(userEntity)) {
                // Lakukan operasi insert
                val userDatabase = UserDatabase.getUserDatabase(applicationContext)
                val userDao = userDatabase.userDao()

                lifecycleScope.launch {
                    try {
                        userDao.registerUser(userEntity)
                        Toast.makeText(applicationContext, USER_REGISTERED_MESSAGE, Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        // Handle database operation error
                        Toast.makeText(applicationContext, "Error registering user", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(applicationContext, "Fill all fields!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInput(userEntity: UserEntity): Boolean {
        // Anda dapat menambahkan logika validasi sesuai kebutuhan di sini
        return true
    }
}
