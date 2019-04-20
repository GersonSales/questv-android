package br.com.questv.ui.analytics;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import br.com.questv.R
import br.com.questv.model.analytics.AnalyticsModel
import br.com.questv.model.user.UserLocalStorage
import br.com.questv.ui.main.fragments.Tab1
import br.com.questv.ui.main.fragments.Tab2

class AnalyticsFragment : Fragment(), AnalyticsView {

  private lateinit var viewPager: ViewPager
  private var presenter = AnalyticsPresenter(this)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_analytics, container, false)

    bindChartsViewPager(view)

    val user = UserLocalStorage(context!!)
    val auth = user.getLoggedUserToken();
    val userId = user.getLoggedUserInfo().id
    this.presenter.gatheringUserAnalytics(userId, auth!!)




    return view
  }

  private fun bindChartsViewPager(view: View) {
    val analyticsAdapter = AnalyticsAdapter(context!!, fragmentManager!!)
    analyticsAdapter.addFragment(Tab1())
    analyticsAdapter.addFragment(Tab2())
    analyticsAdapter.addFragment(Tab1())
    analyticsAdapter.addFragment(Tab2())

    viewPager = view.findViewById(R.id.vp_analytics_charts)
    viewPager.adapter = analyticsAdapter

    bindButtons(view)
  }

  private fun bindButtons(view: View) {
    view.findViewById<ImageButton>(R.id.ib_previous_chart).setOnClickListener { goToPreviousChart() }
    view.findViewById<ImageButton>(R.id.ib_next_chart).setOnClickListener { goToNextChart() }
  }

  private fun goToNextChart() {
    viewPager.currentItem = when {
      viewPager.currentItem < viewPager.adapter!!.count -> viewPager.currentItem + 1
      else -> viewPager.currentItem
    }
  }

  private fun goToPreviousChart() {
    viewPager.currentItem = when {
      viewPager.currentItem > 0 -> viewPager.currentItem - 1
      else -> viewPager.currentItem
    }
  }

  override fun showProgress() {
    showErrorMessage("showProgress")
  }

  override fun hideProgress() {
    showErrorMessage("hideProgress")
  }

  override fun populateCharts(analyticsModel: AnalyticsModel) {
    println(analyticsModel)
    showErrorMessage("populateCharts")
  }

  override fun showErrorMessage(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
  }
}
