package me.muchori.joseph.android_mvvm_login.repository.serializer

import androidx.datastore.CorruptionException
import androidx.datastore.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import me.muchori.joseph.android_mvvm_login.UserDetails
import java.io.InputStream
import java.io.OutputStream

class UserSerializer : Serializer<UserDetails> {
    override fun readFrom(input: InputStream): UserDetails {
        try {
            return UserDetails.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto", exception)
        }
    }

    override fun writeTo(t: UserDetails, output: OutputStream) {
        t.writeTo(output)
    }

}