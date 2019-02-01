package br.com.questv.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import br.com.questv.R
import br.com.questv.ui.main.MainActivity

class SplashActivity : AppCompatActivity(), SplashView {

  private val presenter = SplashPresenter(this, SplashInteractor())

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)
    navigateToMainActivity()
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
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun getContext(): Context? {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun navigateToErrorPage() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun hideProgress() {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
