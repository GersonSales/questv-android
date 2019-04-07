package br.com.questv.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import br.com.questv.R
import br.com.questv.model.series.SeriesRepositoryImpl
import br.com.questv.model.user.SignUpModel
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment(), SignUpView {

  private val presenter = SignUpPresenter(this)

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ) = inflater.inflate(R.layout.fragment_sign_up, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    bt_sign_in.setOnClickListener { navigateToSignIn() }
    bt_sign_up.setOnClickListener {
      this.presenter.registerUser(getSignUpCredentials())
    }
    showBackgroundImage()
  }

  override fun navigateToSignIn() {
    NavHostFragment.findNavController(this).navigate(R.id.nvi_login)
  }

  private fun getSignUpCredentials() = SignUpModel(
    et_sign_up_first_name.text.toString(),
    et_sign_up_last_name.text.toString(),
    et_sign_up_username.text.toString(),
    et_sign_up_email.text.toString(),
    et_sign_up_password.text.toString()
  )

  override fun showBackgroundImage() {
    val url = SeriesRepositoryImpl
      .instance
      .getRandomCoverImage()
      .replace("localhost", "10.0.2.2")

    val imageOptions = DisplayImageOptions.Builder()
      .cacheInMemory(true)
      .cacheOnDisk(true)
      .considerExifParams(true)
      .build()

    val imageLoader = ImageLoader.getInstance()
    imageLoader.displayImage(url, iv_sign_up_bg, imageOptions)
    iv_sign_up_bg?.scaleType = ImageView.ScaleType.CENTER_CROP
  }

  override fun showProgress() {
    pb_sign_up.visibility = VISIBLE
    et_sign_up_first_name.isEnabled = false
    et_sign_up_last_name.isEnabled = false
    et_sign_up_username.isEnabled = false
    et_sign_up_email.isEnabled = false
    et_sign_up_password.isEnabled = false
  }

  override fun hideProgress() {
    pb_sign_up.visibility = GONE
    et_sign_up_first_name.isEnabled = true
    et_sign_up_last_name.isEnabled = true
    et_sign_up_username.isEnabled = true
    et_sign_up_email.isEnabled = true
    et_sign_up_password.isEnabled = true
  }

  override fun setFirstNameError(message: String) {
    et_sign_up_first_name.error = message
  }

  override fun setLastNameError(message: String) {
    et_sign_up_last_name.error = message
  }

  override fun setUsernameError(message: String) {
    et_sign_up_username.error = message
  }

  override fun setEmailError(message: String) {
    et_sign_up_email.error = message
  }

  override fun setPasswordError(message: String) {
    et_sign_up_password.error = message
  }

  override fun showToastMessage(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
  }
}