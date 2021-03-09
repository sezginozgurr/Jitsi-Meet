package com.example.jeetsemeet.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.jeetsemeet.databinding.ActivitySignInBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import java.util.concurrent.Delayed
import kotlin.collections.HashMap

class SignInActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textSignUp.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }
        /*binding.btnSignIn.setOnClickListener {
            val database = FirebaseFirestore.getInstance()
            val user = HashMap<String, Any>()
            user.put("first_name", "John")
            user.put("last_name", "Doe")
            user.put("email", "john.doe@gmail.com")
            database.collection("users")
                .add(user)
                .addOnSuccessListener {
                    Toast.makeText(this,"User İnserted",Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this,"Zıbab Ivj Dene",Toast.LENGTH_SHORT).show()
                }
        }*/
    }
}