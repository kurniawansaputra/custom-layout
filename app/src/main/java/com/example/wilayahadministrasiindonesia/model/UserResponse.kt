package com.example.wilayahadministrasiindonesia.model

import com.google.gson.Gson

data class UserResponse(
	val data: List<User>,
	val meta: Meta
)

data class User(
	val avatarUrl: String,
	val id: Int,
	val username: String
)

data class Meta(
	val code: Int,
	val message: String,
	val status: String
)

fun parseJson(json: String): UserResponse {
	val gson = Gson()
	return gson.fromJson(json, UserResponse::class.java)
}

