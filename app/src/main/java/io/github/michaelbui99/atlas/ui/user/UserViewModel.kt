package io.github.michaelbui99.atlas.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel(){
    var isLoggedIn: MutableLiveData<Boolean> = MutableLiveData(false)

    fun setLogin(value: Boolean){
        isLoggedIn.value = value;
    }

    fun userGrantsAuthPermissions(code: String){

    }
}