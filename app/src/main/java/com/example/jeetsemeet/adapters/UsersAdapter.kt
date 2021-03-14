package com.example.jeetsemeet.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jeetsemeet.R
import com.example.jeetsemeet.model.User

class UsersAdapter(val userList: List<User>) :
    RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    class UsersViewHolder(container: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(container.context)
            .inflate(R.layout.item_container_user, container, false)
    ) {
        private val imageVideoMeet = itemView.findViewById(R.id.imageVideoMeeting) as ImageView
        private val imageAudiMeet = itemView.findViewById(R.id.imageAudiMeeting) as ImageView
        private val txtFirstChar = itemView.findViewById(R.id.txtFirstChar) as TextView
        private val txtUsername = itemView.findViewById(R.id.txtUsername) as TextView
        private val txtEmail = itemView.findViewById(R.id.txtEmail) as TextView


        fun bind(user: User) {

            txtFirstChar.text = user.firstName!!.substring(0, 1)
            txtUsername.text = String.format("%s %s", user.firstName, user.lastName)
            txtEmail.text = user.email
            imageAudiMeet.setImageResource(R.drawable.ic_round_call_24)
            imageVideoMeet.setImageResource(R.drawable.ic_round_videocam_24)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(parent)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int = userList.size
}