package br.com.questv.login

interface LoginView {
  fun showProgress()
  fun hideProgress()
  fun setUserNameError()
  fun setUserPasswordError()
  fun navigateToHome()
}