package br.com.questv.ui.user.profile

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import br.com.questv.R
import br.com.questv.model.user.UserLocalStorage
import br.com.questv.resource.Strings
import br.com.questv.ui.analytics.AnalyticsFragment
import br.com.questv.ui.main.fragments.Tab1
import br.com.questv.ui.user.UserNavigationFragment
import kotlinx.android.synthetic.main.fragment_user_profile.*

class UserProfile : Fragment() {
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
    inflater.inflate(R.layout.fragment_user_profile, container, false)!!

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    val loggedUser = UserLocalStorage(context!!).getLoggedUserInfo()
    tv_user_first_name.text = loggedUser.firstName
    tv_user_last_name.text = loggedUser.lastName
    ib_profile_back.setOnClickListener {
      NavHostFragment.findNavController(this).popBackStack()
    }
    inflateAnalytics()
    super.onViewCreated(view, savedInstanceState)
  }

  private fun inflateAnalytics() {
    fragmentManager?.beginTransaction()
      ?.setCustomAnimations(R.animator.slide_in_top, R.animator.slide_out_bottom, 0, 0)
      ?.addToBackStack(Strings.HOME_FRAGMENT_TAG)
      ?.replace(R.id.fl_profile_analytics, AnalyticsFragment())
      ?.commit()
  }
}