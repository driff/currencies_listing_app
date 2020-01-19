package com.example.listing.core.data

interface CryptoDataSource {

    fun encryptAndEncode(raw: String): String?

    fun decodeAndDecrypt(encrypted: String): String?

}