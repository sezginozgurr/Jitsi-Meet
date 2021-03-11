package com.example.jeetsemeet.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.jeetsemeet.databinding.ActivitySignInBinding
import com.example.jeetsemeet.util.Constant
import com.example.jeetsemeet.util.LocalDataManager
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import java.util.concurrent.Delayed
import kotlin.collections.HashMap

class SignInActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignInBinding
    lateinit var localDataManager: LocalDataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (LocalDataManager.instance.getBoolean(this@SignInActivity, Constant.KEY_IS_SIGNED_IN)) {
            startActivity(Intent(this@SignInActivity, MainActivity::class.java))
        }
        binding.textSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        binding.btnSignIn.setOnClickListener {
            if (isValid()) {
                signIn()
            }
        }
    }

    private fun signIn() {
        binding.btnSignIn.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE

        val database = FirebaseFirestore.getInstance()
        database.collection(Constant.KEY_COLLECTION_USERS)
            .whereEqualTo(Constant.KEY_EMAIL, binding.inputEmail.text.toString())
            .whereEqualTo(Constant.KEY_PASSWORD, binding.inputPassword.text.toString())
            .get()


            .addOnCompleteListener { task ->
                if ((task.isSuccessful) && task.result != null && task.result!!.documents.size > 0) {
                    Log.e("TAG", "${task.result.toString()}")
                    Log.e("TAG", "${task.result!!.documents[0]}")
                    val documentSnapshot: DocumentSnapshot = task.result!!.documents[0]
                    LocalDataManager.instance.putBoolean(
                        this@SignInActivity,
                        Constant.KEY_IS_SIGNED_IN,
                        true
                    )
                    LocalDataManager.instance.putString(
                        this@SignInActivity,
                        Constant.KEY_USER_ID,
                        documentSnapshot.id
                    )
                    LocalDataManager.instance.putString(
                        this@SignInActivity,
                        Constant.KEY_FIRST_NAME,
                        documentSnapshot.getString(Constant.KEY_FIRST_NAME)
                    )
                    LocalDataManager.instance.putString(
                        this@SignInActivity,
                        Constant.KEY_LAST_NAME,
                        documentSnapshot.getString(Constant.KEY_LAST_NAME)
                    )
                    LocalDataManager.instance.putString(
                        this@SignInActivity,
                        Constant.KEY_EMAIL,
                        documentSnapshot.getString(Constant.KEY_EMAIL)
                    )
                    val intent = Intent(this, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                } else {


                    binding.btnSignIn.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                    Toast.makeText(this, "Unabled to sign in", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun isValid(): Boolean {
        if (binding.inputEmail.text.toString().trim().isNullOrEmpty()) {
            Toast.makeText(this, "Boş alan kalmasın", Toast.LENGTH_SHORT).show()
        } else if (binding.inputPassword.text.toString().trim().isNullOrEmpty()) {
            Toast.makeText(this, "Boş alan kalmasın", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}