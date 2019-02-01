package br.com.questv.ui.splash

import android.content.Context

interface SplashView {
  fun navigateToMainActivity()
  fun showProgress()
  fun getContext(): Context?
  fun navigateToErrorPage()
  fun hideProgress()
}