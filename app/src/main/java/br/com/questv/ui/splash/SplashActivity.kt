package br.com.questv.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import br.com.questv.R
import br.com.questv.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), SplashView {

  private val presenter = SplashPresenter(this, SplashInteractor())

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)
    presenter.fetchAllSeries()

  }

  override fun navigateToMainActivity() {
    Handler().postDelayed(object: Thread() {
      override fun run() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        super.run()
      }
    }, 2000)
  }

  override fun showProgress() {
    pb_splash_progress.visibility = VISIBLE
  }

  override fun getContext(): Context? {
    return this
  }

  override fun navigateToErrorPage() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun hideProgress() {
    pb_splash_progress.visibility = GONE
  }
}
