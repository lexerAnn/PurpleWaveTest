package com.purlewave.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SharedPref(private val context: Context) {

    private val mainKey =
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
    private val sharedPref: SharedPreferences = EncryptedSharedPreferences.create(
        context, "${context.packageName}.PREFERENCE_FILE_KEY", mainKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveString(key: String, value: String?) = value?.let {
        with(sharedPref.edit()) {
            putString(key, it)
            apply()
        }
    }

    fun retrieveString(key: String) = sharedPref.getString(key, null)
}