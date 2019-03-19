package br.com.questv.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import android.view.View.VISIBLE
import br.com.questv.R
import br.com.questv.ui.AuxActivity
import br.com.questv.ui.login.LoginActivity
import br.com.questv.ui.main.MainActivity
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), SplashView {

  private val presenter = SplashPresenter(this, SplashInteractor())

  override fun onCreate(savedInstanceState: Bundle?) {
    setContentView(R.layout.activity_splash)
    super.onCreate(savedInstanceState)
    ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this))
    presenter.fetchAllSeries()
  }

  override fun navigateToMainActivity() {
    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
    finish()
  }

  override fun showProgress() {
    pb_splash_progress.visibility = VISIBLE
  }

  override fun getContext(): Context? {
    return this
  }

  override fun navigateToErrorPage() {
    println("Navigate to error page")
  }

  override fun hideProgress() {
    pb_splash_progress.visibility = GONE
  }
}
