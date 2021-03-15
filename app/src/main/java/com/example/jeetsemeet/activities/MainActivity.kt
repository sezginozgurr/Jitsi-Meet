package com.example.jeetsemeet.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jeetsemeet.adapters.UsersAdapter
import com.example.jeetsemeet.databinding.ActivityMainBinding
import com.example.jeetsemeet.model.User
import com.example.jeetsemeet.util.Constant
import com.example.jeetsemeet.util.LocalDataManager
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.iid.FirebaseInstanceId


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val usersList: ArrayList<User> = arrayListOf()

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

        binding.usersRecycler.adapter = UsersAdapter(usersList, { user, position ->
            Toast.makeText(this, "Tıklanan call", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, InComingInvitationActivity::class.java))
        }) { user, position ->
            Toast.makeText(this, "Tıklanan video", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, OutGoingInvitationActivity::class.java))
        }
        getUser()
        binding.swipeReflesh.setOnRefreshListener {
            getUser()
        }
    }

    private fun getUser() {
        val dataBase: FirebaseFirestore = FirebaseFirestore.getInstance()
        dataBase.collection(Constant.KEY_COLLECTION_USERS).get().addOnCompleteListener { task ->
            Toast.makeText(this, "collectiona girdi", Toast.LENGTH_SHORT).show()
            val myUserId: String =
                LocalDataManager.instance.getString(this, Constant.KEY_USER_ID, "default geldi")
            if (task.isSuccessful && task.result != null) {
                for (i in task.result!!) {
                    if (myUserId == i.id) {
                        continue
                    }
                    val user = User()
                    user.firstName = i.getString(Constant.KEY_FIRST_NAME)
                    user.lastName = i.getString(Constant.KEY_LAST_NAME)
                    user.email = i.getString(Constant.KEY_EMAIL)
                    user.token = i.getString(Constant.KEY_USER_ID)
                    usersList.add(user)
                }
                if (usersList.size > 0) {
                    Toast.makeText(this, "0 dan büyük", Toast.LENGTH_SHORT).show()
                    binding.usersRecycler.adapter = UsersAdapter(usersList, { user, position ->
                        Toast.makeText(this, "Tıklanan call", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, InComingInvitationActivity::class.java)
                        intent.putExtra("user",user)
                        intent.putExtra("type","video")
                        startActivity(intent)
                    }) { user, position ->
                        Toast.makeText(this, "Tıklanan video", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, OutGoingInvitationActivity::class.java)
                        intent.putExtra("user",user)
                        intent.putExtra("type","video")
                        startActivity(intent)
                    }
                } else {
                    binding.txtErrorMessage.visibility = View.VISIBLE
                }
            } else {
                binding.txtErrorMessage.text = String.format("%s", "No Users avaible")
                binding.txtErrorMessage.visibility = View.VISIBLE
            }
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