package br.com.questv.ui.user.profile

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.questv.R
import br.com.questv.model.user.UserLocalStorage
import kotlinx.android.synthetic.main.fragment_user_profile.*

class UserProfile : Fragment() {
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
    inflater.inflate(R.layout.fragment_user_profile, container, false)!!

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    val loggedUser = UserLocalStorage(context!!).getLoggedUserInfo()
    tv_user_first_name.text = loggedUser.firstName
    tv_user_last_name.text = loggedUser.lastName
    super.onViewCreated(view, savedInstanceState)
  }
}