package br.com.questv.ui.login

import br.com.questv.security.Token

class LoginPresenter(var loginView: LoginView?, private val loginInteractor: LoginInteractor) :
  LoginInteractor.OnLoginFinishedListener {

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

  override fun onSuccess(token: Token) {
    loginView?.apply {
      hideProgress()
      navigateToHome(token)
    }
  }
}