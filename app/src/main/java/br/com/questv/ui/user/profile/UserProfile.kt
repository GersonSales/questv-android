package br.com.questv.ui.user.profile

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.questv.R
import br.com.questv.model.user.UserLocalStorage

class UserProfile : Fragment() {
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_user_profile, container, false)
    val loggedUser = UserLocalStorage(context!!).getLoggedUserInfo()
    view.findViewById<TextView>(R.id.tv_user_name).text = loggedUser.firstName
    return view
  }
}