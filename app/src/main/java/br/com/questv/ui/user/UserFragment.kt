package br.com.questv.ui.user

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment
import br.com.questv.R
import br.com.questv.model.user.UserLocalStorage
import br.com.questv.resource.Strings
import br.com.questv.ui.login.LoginActivity
import br.com.questv.ui.main.MainView

class UserFragment : Fragment(), UserView {


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    super.onCreateView(inflater, container, savedInstanceState)
    val view = inflater.inflate(R.layout.fragment_user, container, false)
    val imageButton: ImageButton = view.findViewById(R.id.ib_close_user_acc_fragment)
    imageButton.setOnClickListener { exitFromStack() }
    bindComponentsBehavior(view)
    return view
  }

  private fun bindComponentsBehavior(view: View) {
    view.findViewById<TextView>(R.id.tv_user_profile_opt).setOnClickListener {
      NavHostFragment.findNavController(this).navigate(R.id.user_profile_fragment)
    }

    view.findViewById<TextView>(R.id.tv_switch_acc_opt).setOnClickListener {
      activity!!.finish()
      UserLocalStorage(context!!).logout()
      startActivity(Intent(context, LoginActivity::class.java))
    }
  }

  override fun exitFromStack() {
    fragmentManager?.beginTransaction()
      ?.setCustomAnimations(R.animator.slide_in_bottom, R.animator.slide_out_top, 0, 0)
      ?.remove(this)?.commit()
    fragmentManager?.popBackStack()
  }
}