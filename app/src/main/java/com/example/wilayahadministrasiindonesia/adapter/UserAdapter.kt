package com.example.wilayahadministrasiindonesia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wilayahadministrasiindonesia.databinding.ItemRowUserBinding
import com.example.wilayahadministrasiindonesia.model.User

class UserAdapter(private val userList: List<User>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    class ViewHolder (val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(userList[position]) {
                val user = username

                binding.apply {
                    textName.text = "$id. $user"
                }
            }
        }
    }
}