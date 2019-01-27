package br.com.questv.ui.login

interface LoginView {
  fun showProgress()
  fun hideProgress()
  fun setUserNameError()
  fun setUserPasswordError()
  fun navigateToHome()
}