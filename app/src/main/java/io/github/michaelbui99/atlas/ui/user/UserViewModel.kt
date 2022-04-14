package io.github.michaelbui99.atlas.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.michaelbui99.atlas.model.repositories.AuthRepositoryImpl
import io.reactivex.rxjava3.kotlin.subscribeBy

class UserViewModel : ViewModel() {
    private var accessToken: String? = null
    var isLoggedIn: MutableLiveData<Boolean> = MutableLiveData(false)
    var error: MutableLiveData<String> = MutableLiveData()

    fun setLogin(value: Boolean) {
        isLoggedIn.value = value;
    }

    fun userGrantsAuthPermissions(code: String) {
        AuthRepositoryImpl.authCode = code
        AuthRepositoryImpl.getAccessToken().subscribeBy(
            onNext = {
                this.accessToken = it.accessToken
                isLoggedIn.postValue(true)
            },
            onError = {
                this.error.postValue(it.message.toString())
            }
        )
    }

    fun getAuthUrl(): String {
        return AuthRepositoryImpl.getAuthUrl()
    }
}