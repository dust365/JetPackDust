package com.jetpack.dust.store

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.jetpack.dust.SettingPreferences
import java.io.InputStream
import java.io.OutputStream

object SettingBeanSerializer:Serializer<SettingPreferences> {

    override val defaultValue: SettingPreferences
        get() = SettingPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): SettingPreferences {
        try {
            return SettingPreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }

    }

    override suspend fun writeTo(t: SettingPreferences, output: OutputStream) {
        t.writeTo(output)
    }


}