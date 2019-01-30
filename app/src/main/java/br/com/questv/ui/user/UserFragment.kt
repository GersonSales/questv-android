package br.com.questv.ui.user

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import br.com.questv.R

class UserFragment : Fragment(), UserView {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    super.onCreateView(inflater, container, savedInstanceState)
    val view = inflater.inflate(R.layout.fragment_user, container, false)
    val imageButton:ImageButton = view.findViewById(R.id.ib_close_user_acc_fragment)
    imageButton.setOnClickListener { exitFromStack() }
    return view
  }

  override fun exitFromStack() {
    fragmentManager?.beginTransaction()
      ?.setCustomAnimations(R.animator.slide_in_bottom, R.animator.slide_out_top, 0, 0)
      ?.remove(this)?.commit()
    fragmentManager?.popBackStack()
  }
}