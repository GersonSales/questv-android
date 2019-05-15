package br.com.questv.ui.splash

import android.content.Context

interface SplashView {
  fun navigateToMainActivity()
  fun showProgress()
  fun showToastMessage(message: String)
  fun getContext(): Context?
  fun navigateToErrorPage()
  fun hideProgress()
  fun navigateToUnavailableServicePage()
}