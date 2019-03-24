package br.com.questv.ui.signup

interface SignUpView {
  fun showProgress()
  fun hideProgress()
  fun setFirstNameError(message: String)
  fun setLastNameError(message: String)
  fun setUsernameError(message: String)
  fun setEmailError(message: String)
  fun setPasswordError(message: String)
  fun navigateToSignIn()
  fun showToastMessage(message: String)
  fun showBackgroundImage()
}