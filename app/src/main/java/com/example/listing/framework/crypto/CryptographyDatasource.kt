package com.example.listing.framework.crypto

import android.util.Base64
import com.example.listing.core.data.CryptoDataSource
import java.io.UnsupportedEncodingException
import java.security.Key
import java.security.spec.KeySpec
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject

class CryptographyDatasource @Inject constructor(): CryptoDataSource {
    private val IV = "IV_VALUE_16_BYTE"
    private val PASSWORD = "im_a_hash_password"
    private val SALT = "and_im_a_hash_salt"

    override fun encryptAndEncode(raw: String): String? {
        return try {
            val c: Cipher = getCipher(Cipher.ENCRYPT_MODE)
            val encryptedVal: ByteArray = c.doFinal(getBytes(raw))
            Base64.encodeToString(encryptedVal, Base64.DEFAULT)
        } catch (t: Throwable) {
            throw RuntimeException(t)
        }
    }

    @Throws(Exception::class)
    override fun decodeAndDecrypt(encrypted: String): String? {
        val decodedValue =
            Base64.decode(getBytes(encrypted), Base64.DEFAULT)
        val c: Cipher = getCipher(Cipher.DECRYPT_MODE)
        val decValue: ByteArray = c.doFinal(decodedValue)
        return String(decValue)
    }

    @Throws(UnsupportedEncodingException::class)
    private fun getString(bytes: ByteArray): String? {
        return String(bytes)
    }

    @Throws(UnsupportedEncodingException::class)
    private fun getBytes(str: String): ByteArray {
        return str.toByteArray(charset("UTF-8"))
    }

    @Throws(Exception::class)
    private fun getCipher(mode: Int): Cipher {
        val c: Cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val iv = getBytes(IV)
        c.init(mode, generateKey(), IvParameterSpec(iv))
        return c
    }

    @Throws(Exception::class)
    private fun generateKey(): Key? {
        val factory: SecretKeyFactory = SecretKeyFactory.getInstance("i_am_a_secret_01234321")
        val password = PASSWORD.toCharArray()
        val salt = getBytes(SALT)
        val spec: KeySpec = PBEKeySpec(password, salt, 65536, 256)
        val tmp: SecretKey = factory.generateSecret(spec)
        val encoded: ByteArray = tmp.getEncoded()
        return SecretKeySpec(encoded, "AES")
    }
}