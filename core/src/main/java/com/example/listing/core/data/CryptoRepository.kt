package com.example.listing.core.data

class CryptoRepository (private val dataSource: CryptoDataSource) {

    fun encryptString(raw: String): String? = dataSource.encryptAndEncode(raw)

    fun decryptString(encrypted: String): String? = dataSource.decodeAndDecrypt(encrypted)

}