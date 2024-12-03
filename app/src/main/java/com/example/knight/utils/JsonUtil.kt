package com.example.knight.utils

import com.google.gson.Gson

inline fun <reified T> fromJson(json: String): T {
    return Gson().fromJson(json, T::class.java)
}

fun <T> toJson(data: T): String {
    return Gson().toJson(data)
}