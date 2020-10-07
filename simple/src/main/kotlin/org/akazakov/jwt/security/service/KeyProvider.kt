package org.akazakov.jwt.security.service

import java.security.Key

interface KeyProvider {
    fun getKey(): Key
}