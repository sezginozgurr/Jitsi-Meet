package com.example.jeetsemeet.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.jeetsemeet.databinding.ActivitySignUpBinding
import com.example.jeetsemeet.util.Constant
import com.example.jeetsemeet.util.LocalDataManager
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSignUp.setOnClickListener {
            if (isValid()) {
                signUp()
            }
        }
    }

    fun isValid(): Boolean {
        if (binding.firstName.text.trim().isNullOrEmpty()) {
            Toast.makeText(this, "Lütfen boşlukları doldurun.", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.lastName.text.trim().isNullOrEmpty()) {
            Toast.makeText(this, "Lütfen boşlukları doldurun.", Toast.LENGTH_SHORT).show()
            return false
        } else if (binding.password.text.trim().isNullOrEmpty()) {
            Toast.makeText(this, "Lütfen boşlukları doldurun.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun signUp() {
        binding.btnSignUp.visibility = View.VISIBLE
        binding.progressBar.visibility = View.VISIBLE

        val database = FirebaseFirestore.getInstance()
        val users = HashMap<String, Any>()
        users[Constant.KEY_FIRST_NAME] = binding.firstName.text.toString()
        users[Constant.KEY_LAST_NAME] = binding.lastName.text.toString()
        users[Constant.KEY_EMAIL] = binding.email.text.toString()
        users[Constant.KEY_PASSWORD] = binding.password.text.toString()



        database.collection(Constant.KEY_COLLECTION_USERS).add(users)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "User İnserted", Toast.LENGTH_SHORT).show()
                LocalDataManager.instance.putString(
                    this@SignUpActivity,
                    Constant.KEY_FIRST_NAME,
                    binding.firstName.text.trim().toString()
                )
                LocalDataManager.instance.putString(
                    this@SignUpActivity,
                    Constant.KEY_LAST_NAME,
                    binding.lastName.text.trim().toString()
                )
                LocalDataManager.instance.putString(this@SignUpActivity, Constant.KEY_USER_ID, documentReference.id )
                LocalDataManager.instance.putString(
                    this@SignUpActivity,
                    Constant.KEY_EMAIL,
                    binding.email.text.trim().toString()
                )
                LocalDataManager.instance.putString(
                    this@SignUpActivity,
                    Constant.KEY_PASSWORD,
                    binding.password.text.trim().toString()
                )
                LocalDataManager.instance.putBoolean(
                    this@SignUpActivity,
                    Constant.KEY_IS_SIGNED_IN,
                    true
                )
                startActivity(Intent(this, MainActivity::class.java))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            }.addOnFailureListener {
                binding.btnSignUp.visibility = View.VISIBLE
                binding.progressBar.visibility = View.INVISIBLE
                Toast.makeText(this, "Error: Zıbab Ivj", Toast.LENGTH_SHORT).show()
            }
    }
}