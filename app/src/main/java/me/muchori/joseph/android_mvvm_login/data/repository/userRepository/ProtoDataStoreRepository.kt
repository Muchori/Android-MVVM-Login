package me.muchori.joseph.android_mvvm_login.data.repository.userRepository

import android.content.Context
import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import me.muchori.joseph.android_mvvm_login.UserDetails
import me.muchori.joseph.android_mvvm_login.data.repository.serializer.UserSerializer
import me.muchori.joseph.android_mvvm_login.model.user.User
import java.io.IOException

class ProtoDataStoreRepository(context: Context) {

    private val dataStore: DataStore<UserDetails> = context.createDataStore(
        "user_details",
        serializer = UserSerializer()
    )
    val readProto: Flow<UserDetails> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.d("Error", exception.message.toString())
                emit(UserDetails.getDefaultInstance())
            } else {
                throw exception
            }
        }

    suspend fun loginUpdateUserDetails(user: User) {
        dataStore.updateData { preference ->
            preference.toBuilder()
                .setId(user.data.user.id)
                .setFirstName(user.data.user.firstName)
                .setLastName(user.data.user.lastName)
                .setEmail(user.data.user.email)
                .setPhone(user.data.user.phone)
                .setGender(user.data.user.gender)
                .setUserType(user.data.user.userType)
                .setAccessToken(user.data.payload.accessToken)
                .setRefreshToken(user.data.payload.refreshToken)
                .build()
        }
    }

    suspend fun clear() {
        dataStore.updateData { preference ->
            preference.toBuilder()
                .clear()
                .build()
        }
    }
}