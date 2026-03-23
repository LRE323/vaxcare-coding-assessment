package com.example.vaxcare.utils

import java.util.Locale

fun Float.toDollarFormat(): String = String.format(Locale.US, "$%.2f", this)
