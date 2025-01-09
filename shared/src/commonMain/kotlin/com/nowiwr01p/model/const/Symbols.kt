package com.nowiwr01p.model.const

object Symbols {

    const val ALPHABET = "abcdefghijklmpopqrstuvwxyz"
    const val DIGITS = "1234567890"
    const val SPECIAL_CHARACTERS = "!@#$%^&*()-_=+{}[]|/"

    val allSymbols = listOf(
        ALPHABET, DIGITS, ALPHABET.uppercase(), SPECIAL_CHARACTERS
    )
}