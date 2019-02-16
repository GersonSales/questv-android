package br.com.questv.ui.login

import br.com.questv.security.Token

interface LoginView {
  fun showProgress()
  fun hideProgress()
  fun setUserNameError()
  fun setUserPasswordError()
  fun navigateToHome(token: Token)
}