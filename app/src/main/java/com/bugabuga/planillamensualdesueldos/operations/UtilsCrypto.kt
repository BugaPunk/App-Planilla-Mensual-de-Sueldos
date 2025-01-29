package com.bugabuga.planillamensualdesueldos.operations

import java.security.MessageDigest

object UtilsCrypto {
    fun hashSHA256(input: String): String {
        val bytes = input.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }
}