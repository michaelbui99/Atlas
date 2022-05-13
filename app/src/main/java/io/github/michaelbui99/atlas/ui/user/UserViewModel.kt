package io.github.michaelbui99.atlas.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.michaelbui99.atlas.model.auth.RedditAuthStore
import io.github.michaelbui99.atlas.model.domain.user.RedditUser
import io.github.michaelbui99.atlas.model.repositories.AccountRepository
import io.github.michaelbui99.atlas.model.repositories.AccountRepositoryImpl
import io.github.michaelbui99.atlas.model.repositories.AuthRepositoryImpl
import io.github.michaelbui99.atlas.model.repositories.RedditRepositoryImpl
import io.reactivex.rxjava3.kotlin.subscribeBy

class UserViewModel : ViewModel() {
    private val accountRepository: AccountRepository = AccountRepositoryImpl.getInstance()
    var isLoggedIn: MutableLiveData<Boolean> = MutableLiveData(false)
    var error: MutableLiveData<String> = MutableLiveData()
    var user: MutableLiveData<RedditUser?> = MutableLiveData(null)


    /**
     * Passes the auth code to repository, where after code is used to retrieve access token
     * and login state is updated
     * This method is only called if user has granted the app the necessary permissions
     *
     * @param code The retrieved code after user grants the app permission.
     *              Used for retrieving access token
     * */
    fun userGrantsAuthPermissions(code: String) {
        if (RedditAuthStore.getAuthCode() != null) {
            return
        }

        AuthRepositoryImpl.setAuthCode(code)
        AuthRepositoryImpl.getAccessToken().subscribeBy(
            onNext = {
                isLoggedIn.postValue(AuthRepositoryImpl.userIsLoggedIn())
                RedditRepositoryImpl.getMe().subscribeBy {
                    user.postValue(it)
                    accountRepository.ensureUserHasLocalAccount(it.displayName)
                }
            },
            onError = {
                this.error.postValue(it.message.toString())
            },
        )
    }


    /**
     * Fetches the url used to start the oauth flow, where user grants the app the necessary
     * permissions
     *
     * @return Url used to start oauth flow
     * */
    fun getAuthUrl(): String {
        return AuthRepositoryImpl.getAuthUrl()
    }
}