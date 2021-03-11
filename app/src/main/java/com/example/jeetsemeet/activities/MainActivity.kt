package com.example.jeetsemeet.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jeetsemeet.databinding.ActivityMainBinding
import com.example.jeetsemeet.util.Constant
import com.example.jeetsemeet.util.LocalDataManager
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.iid.FirebaseInstanceId


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.txtTitle.text = String.format(
            "%s %s", LocalDataManager.instance.getString(
                this@MainActivity,
                Constant.KEY_FIRST_NAME, Constant.KEY_FIRST_NAME
            ),
            LocalDataManager.instance.getString(
                this@MainActivity,
                Constant.KEY_LAST_NAME, ""
            )
        )
        FirebaseInstanceId.getInstance().instanceId
            .addOnSuccessListener { instanceIdResult ->
                val token = instanceIdResult.token
                sendFCMTokenToDatabase(token)
            }
    }

    private fun sendFCMTokenToDatabase(token: String) {
        val database: FirebaseFirestore = FirebaseFirestore.getInstance()
        val documentReference: DocumentReference =
            database.collection(Constant.KEY_COLLECTION_USERS).document(
                LocalDataManager.instance.getString(
                    this@MainActivity,
                    Constant.KEY_USER_ID,
                    Constant.KEY_USER_ID
                )
            )
        documentReference.update(Constant.KEY_FCM_TOKEN, token).addOnSuccessListener {
            Toast.makeText(this, "Token updated succesfull", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Toast.makeText(this, "Token not succesfull", Toast.LENGTH_SHORT).show()
        }
    }
}