package com.gumigames.presentation.util

import java.text.NumberFormat
import java.util.Locale

fun addSirToName(name: String): String {
    return "${name}님"
}

fun nameAndGenderFormat(name: String, gender: String): String{
    return "${name}(${gender})"
}

fun levelAndExpFormat(level: Int, exp: Int): String{
    return "Lv.${level}(Exp:${exp})"
}

fun numberFormat(number: Int): String{
    val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
    return numberFormat.format(number.toLong())
}