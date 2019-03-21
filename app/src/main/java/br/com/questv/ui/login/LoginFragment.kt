package br.com.questv.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.NavHostFragment
import br.com.questv.R
import br.com.questv.model.series.SeriesRepositoryImpl
import br.com.questv.ui.main.MainActivity
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import kotlinx.android.synthetic.main.fragment_login.*
import android.graphics.Bitmap
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.assist.ImageScaleType


class LoginFragment : Fragment(), LoginView {

  private val presenter = LoginPresenter(this, LoginInteractor())

  override fun onCreateView(inflater: LayoutInflater,
                            container: ViewGroup?,
                            savedInstanceState: Bundle?)
      = inflater.inflate(R.layout.fragment_login, container, false)!!

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    bt_login.setOnClickListener { validateCredentials() }
    bt_sign_up.setOnClickListener { navigateToSignUp() }

    showBackgroundImage()
    super.onViewCreated(view, savedInstanceState)
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
    val intent = Intent(context, MainActivity::class.java)
    activity?.startActivity(intent)
    activity?.finish()
  }

  override fun navigateToSignUp() {
    NavHostFragment.findNavController(this).navigate(R.id.nvi_sign_up)
  }

  override fun onDestroy() {
    this.presenter.onDestroy()
    super.onDestroy()
  }

  private fun showBackgroundImage() {

//    val url = "http://10.0.2.2:5000/series/34/promoImage"
    val url = SeriesRepositoryImpl
      .instance
      .getRandomPromoImage()
      .replace("localhost", "10.0.2.2")

    val imageLoader = ImageLoader.getInstance()
    imageLoader.displayImage(url, iv_login_bg)
    iv_login_bg?.scaleType = ImageView.ScaleType.CENTER_CROP
  }

  override fun getViewContext() = context!!
}
