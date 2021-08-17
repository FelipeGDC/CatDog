package com.fgdc.core.utils.extensions

import java.util.*

fun String.Companion.empty() = ""

fun String.containsText(text: String): Boolean {
    return this.lowercase(Locale.getDefault()).contains(text)
}
