package br.com.questv.login

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
      setUserPasswordError()
    }
  }

  override fun onUserPasswordError() {
    loginView?.apply {
      hideProgress()
      setUserNameError()
    }
  }

  override fun onSuccess() {
    loginView?.apply {
      hideProgress()
      navigateToHome()
    }
  }
}