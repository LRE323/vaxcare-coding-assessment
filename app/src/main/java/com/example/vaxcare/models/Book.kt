package com.example.vaxcare.models

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val status: BookStatus,
    val fee: Float,
    val lastEdited: String
)

