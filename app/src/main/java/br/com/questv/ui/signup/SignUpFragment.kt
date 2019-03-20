package br.com.questv.ui.signup

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import br.com.questv.R
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment(), SignUpView {

  override fun onCreateView(inflater: LayoutInflater,
                            container: ViewGroup?,
                            savedInstanceState: Bundle?)
      = inflater.inflate(R.layout.fragment_sign_up, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    bt_sign_in.setOnClickListener { navigateToSignIn() }
    bindPasswordVisibilityBehavior()
    super.onViewCreated(view, savedInstanceState)
  }

  private fun bindPasswordVisibilityBehavior() {
  }

  override fun navigateToSignIn() {
    NavHostFragment.findNavController(this).navigate(R.id.nvi_login)
  }
}