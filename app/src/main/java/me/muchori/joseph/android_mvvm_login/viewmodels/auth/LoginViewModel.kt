package me.muchori.joseph.android_mvvm_login.viewmodels.auth

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.muchori.joseph.android_mvvm_login.model.user.User
import me.muchori.joseph.android_mvvm_login.network.api.LoginApi
import me.muchori.joseph.android_mvvm_login.network.retrofit.WebServiceClient
import me.muchori.joseph.android_mvvm_login.repository.userRepository.DataStoreRepository
import me.muchori.joseph.android_mvvm_login.repository.userRepository.ProtoDataStoreRepository
import me.muchori.joseph.android_mvvm_login.util.SingleLiveEvent
import me.muchori.joseph.android_mvvm_login.util.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(
    application: Application
) : AndroidViewModel(application), Callback<User> {

    private val repository = DataStoreRepository(application)
    private val protoRepository = ProtoDataStoreRepository(application)

    var btnSelected: ObservableBoolean? = null
    var email: ObservableField<String>? = null
    var password: ObservableField<String>? = null
    var progressDialog: SingleLiveEvent<Boolean>? = null
    var userLogin: MutableLiveData<User>? = null

    //read data from preference datastore
    val readFromDataStore = repository.readIdFromDataStore.asLiveData()

    init {
        btnSelected = ObservableBoolean(false)
        progressDialog = SingleLiveEvent<Boolean>()
        email = ObservableField("")
        password = ObservableField("")
        userLogin = MutableLiveData<User>()
    }

    fun updateUserDetails(user: User) = viewModelScope.launch(Dispatchers.IO) {
        protoRepository.loginUpdateUserDetails(user)
        Log.d("ProtoDataStore", user.toString())
    }

    fun saveRefreshToken(refresh_token: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.saveRefreshToken(refresh_token)
    }

    fun saveToDataStore(id: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.saveToDataStore(id)
    }

    fun onEmailChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        btnSelected?.set(Util.isEmailValid(s.toString()) && password?.get()!!.length >= 4)
    }

    fun onPasswordChanged(s: CharSequence, start: Int, befor: Int, count: Int) {
        btnSelected?.set(Util.isEmailValid(email?.get()!!) && s.toString().length >= 4)
    }

    fun login() {
        progressDialog?.value = true
        WebServiceClient.client.create(LoginApi::class.java)
            .login(email = email?.get()!!, password = password?.get()!!)
            .enqueue(this)
    }

    override fun onResponse(call: Call<User>?, response: Response<User>?) {
        progressDialog?.value = false
        if(response!!.isSuccessful){
            try {
                userLogin?.value = response.body()
                Log.d("ResponseBody ", response.body().toString())
            }
            catch (e: Exception){
            }
        } else{
        }
    }

    override fun onFailure(call: Call<User>?, t: Throwable?) {
        progressDialog?.value = false
    }
}