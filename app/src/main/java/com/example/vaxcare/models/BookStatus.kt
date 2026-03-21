package com.example.vaxcare.models

import kotlinx.serialization.Serializable

@Serializable
data class BookStatus(
    val id: Int,
    val displayText: String,

    // These may not be present in ever status response, so they are nullable.
    val timeCheckedIn: String? = null,
    val timeCheckedOut: String? = null,
    val dueDate: String? = null
)