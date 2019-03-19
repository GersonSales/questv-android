package br.com.questv.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import br.com.questv.R
import br.com.questv.model.series.SeriesRepositoryImpl
import br.com.questv.ui.main.MainActivity
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

  private val presenter = LoginPresenter(this, LoginInteractor())

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)

    bt_login.setOnClickListener { validateCredentials() }
    showBackgroundImage()
  }

  private fun validateCredentials() {
    this.presenter.validateCredentials(et_username.text.toString(), et_password.text.toString());
  }

  override fun showProgress() {
    et_username.isEnabled = false
    et_password.isEnabled = false
    bt_login.isEnabled = false
    pb_login.visibility = View.VISIBLE
  }

  override fun hideProgress() {
    et_username.isEnabled = true
    et_password.isEnabled = true
    bt_login.isEnabled = true
    pb_login.visibility = View.GONE
  }

  override fun setUserNameError() {
    et_username.error = getString(R.string.username_error)
  }

  override fun setUserPasswordError() {
    et_password.error = getString(R.string.password_error)
  }

  override fun navigateToHome() {
    startActivity(Intent(this, MainActivity::class.java))
    finish()
  }

  override fun onDestroy() {
    this.presenter.onDestroy()
    super.onDestroy()
  }

  override fun getContext(): Context {
    return this
  }

  private fun showBackgroundImage() {

    val url = "http://10.0.2.2:5000/series/1434/promoImage";

    val imageLoader = ImageLoader.getInstance()
    imageLoader.displayImage(url, iv_login_bg)
    iv_login_bg?.scaleType = ImageView.ScaleType.CENTER_CROP
  }
}
