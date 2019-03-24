package br.com.questv.ui.login

import android.content.Context
import br.com.questv.security.Token

interface LoginView {
  fun showProgress()
  fun hideProgress()
  fun setUserNameError()
  fun setUserPasswordError()
  fun navigateToHome()
  fun navigateToSignUp()
  fun getViewContext(): Context
  fun showToastMessage(message: String)

}