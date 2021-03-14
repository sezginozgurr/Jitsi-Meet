package com.example.jeetsemeet.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jeetsemeet.R
import com.example.jeetsemeet.model.User

class UsersAdapter(
    private var userList: List<User>,
    private val callClick: (user: User, position: Int) -> Unit,
    private val videoClick: (user: User, position: Int) -> Unit
) :
    RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    class UsersViewHolder(container: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(container.context)
            .inflate(R.layout.item_container_user, container, false)
    ) {
        val imageVideoMeet = itemView.findViewById(R.id.imageVideoMeeting) as ImageView
        val imageAudiMeet = itemView.findViewById(R.id.imageAudiMeeting) as ImageView
        val txtFirstChar = itemView.findViewById(R.id.txtFirstChar) as TextView
        var txtUsername = itemView.findViewById(R.id.txtUsername) as TextView
        val txtEmail = itemView.findViewById(R.id.txtEmail) as TextView


        fun bind(
            user: User,
            position: Int,
            itemClickCall: (user: User, position: Int) -> Unit,
            itemClickVideo: (user: User, position: Int) -> Unit
        ) {

            txtFirstChar.text = user.firstName!!.substring(0, 1)
            txtUsername.text = String.format("%s %s", user.firstName, user.lastName)
            txtEmail.text = user.email
            imageAudiMeet.setImageResource(R.drawable.ic_round_call_24)
            imageVideoMeet.setImageResource(R.drawable.ic_round_videocam_24)
            imageAudiMeet.setOnClickListener {
                itemClickCall(user, position)
            }
            imageVideoMeet.setOnClickListener {
                itemClickVideo(user, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(parent)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(
            userList[position],
            position,
            itemClickCall = callClick,
            itemClickVideo = videoClick
        )
    }

    override fun getItemCount(): Int = userList.size
}