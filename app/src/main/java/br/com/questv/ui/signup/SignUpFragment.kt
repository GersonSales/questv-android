package br.com.questv.ui.signup

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.NavHostFragment
import br.com.questv.R
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment(), SignUpView {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ) = inflater.inflate(R.layout.fragment_sign_up, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    bt_sign_in.setOnClickListener { navigateToSignIn() }
    bindPasswordVisibilityBehavior()
    showBackgroundImage()
    super.onViewCreated(view, savedInstanceState)
  }

  private fun bindPasswordVisibilityBehavior() {
  }

  override fun navigateToSignIn() {
    NavHostFragment.findNavController(this).navigate(R.id.nvi_login)
  }

  override fun showBackgroundImage() {

    val url = "http://10.0.2.2:5000/series/34/promoImage"

    val imageLoader = ImageLoader.getInstance()
    imageLoader.displayImage(url, iv_sign_up_bg)
    iv_sign_up_bg?.scaleType = ImageView.ScaleType.CENTER_CROP
  }
}