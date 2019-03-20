package br.com.questv.ui.login

import br.com.questv.model.user.UserLocalStorage
import com.auth0.android.jwt.JWT

class LoginPresenter(var loginView: LoginView?, private val loginInteractor: LoginInteractor) :
  LoginInteractor.OnLoginFinishedListener,
  UserLocalStorage.OnUserDetailsConsumptionListener {
  fun validateCredentials(username: String, password: String) {
    loginView?.showProgress()
    loginInteractor.login(username, password, this)
  }

  fun onDestroy() {
    loginView = null
  }

  override fun onUserNameError() {
    loginView?.apply {
      hideProgress()
      setUserNameError()
    }
  }

  override fun onUserPasswordError() {
    loginView?.apply {
      hideProgress()
      setUserPasswordError()
    }
  }

  override fun onSuccess(token: JWT) {
    storeLoggedUser(token)
  }

  private fun storeLoggedUser(token: JWT) {
    val userLocalStorage = UserLocalStorage(loginView?.getViewContext()!!)
    userLocalStorage.login(token, this)
  }

  override fun onUserDetailsSuccess() {
    loginView?.apply {
      hideProgress()
      navigateToHome()
    }
  }

  override fun onUserDetailsFail() {
    loginView?.apply {
      hideProgress()
      setUserPasswordError()
    }
  }
}