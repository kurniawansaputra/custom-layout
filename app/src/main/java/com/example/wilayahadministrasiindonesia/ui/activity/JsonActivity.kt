package com.example.wilayahadministrasiindonesia.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.wilayahadministrasiindonesia.R
import com.example.wilayahadministrasiindonesia.adapter.UserAdapter
import com.example.wilayahadministrasiindonesia.databinding.ActivityJsonBinding
import com.example.wilayahadministrasiindonesia.model.parseJson
import okio.IOException

class JsonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJsonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJsonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        setList()
    }

    private fun setList() {
        try {
            val jsonString = resources.openRawResource(R.raw.user1).bufferedReader().use { it.readText() }
//            val jsonArray = JSONArray(jsonString)
//            val jsonData = mutableListOf<UserResponse>()
//            for (i in 0 until jsonArray.length()) {
//                val item = jsonArray.getJSONObject(i)
//                val id = item.getInt("id")
//                val firstName = item.getString("first_name")
//                val lastName = item.getString("last_name")
//                val email = item.getString("email")
//                jsonData.add(UserResponse(id, firstName, lastName, email))
//            }
//
//            binding.recyclerView.adapter = UserAdapter(jsonData)

            val userResponse = parseJson(jsonString)
            Log.d("jsonString", userResponse.toString())
            val userAdapter = UserAdapter(userResponse.data)
            binding.recyclerView.adapter = userAdapter
            binding.recyclerView.setHasFixedSize(true)

        } catch (_: IOException) {

        }
    }
}