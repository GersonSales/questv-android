package br.com.questv.ui.signup

import br.com.questv.model.user.SignUpModel

class SignUpPresenter(private val signUpView: SignUpView)
  : SignUpInteractor.OnSignUpFinishedListener {
  private val signUpInteractor = SignUpInteractor()


  fun registerUser(signUpModel: SignUpModel) {
    this.signUpView.showProgress()
    this.signUpInteractor.registerUser(signUpModel, this)
  }

  override fun onFirstNameError(message: String) {
    this.signUpView.apply {
      hideProgress()
      setFirstNameError(message)
    }
  }

  override fun onlastNameError(message: String) {
    this.signUpView.apply {
      hideProgress()
      setLastNameError(message)
    }
  }

  override fun onUsernameError(message: String) {
    this.signUpView.apply {
      hideProgress()
      setUsernameError(message)
    }
  }

  override fun onEmailError(message: String) {
    this.signUpView.apply {
      hideProgress()
      setEmailError(message)
    }
  }

  override fun onPasswordError(message: String) {
    this.signUpView.apply {
      hideProgress()
      setPasswordError(message)
    }
  }

  override fun onSuccess() {
    this.signUpView.apply {
      hideProgress()
      showToastMessage("User successfully registered.")
      navigateToSignIn()
    }
  }
}