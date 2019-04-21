package br.com.questv.ui.analytics;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.questv.R
import br.com.questv.ui.analytics.model.AnalyticsModel
import br.com.questv.model.user.UserLocalStorage
import br.com.questv.resource.Strings.ANALYTICS_BUNDLE
import br.com.questv.ui.main.fragments.*
import kotlinx.android.synthetic.main.fragment_analytics.*

class AnalyticsFragment : Fragment(), AnalyticsView {

  private var presenter = AnalyticsPresenter(this)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    return inflater.inflate(R.layout.fragment_analytics, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    val user = UserLocalStorage(context!!)
    val auth = user.getLoggedUserToken();
    val userId = user.getLoggedUserInfo().id
    this.presenter.gatheringUserAnalytics(userId, auth!!)
    super.onViewCreated(view, savedInstanceState)
  }

  private fun bindChartsViewPager(analyticsModel: AnalyticsModel) {
    val analyticsAdapter = AnalyticsAdapter(context!!, fragmentManager!!)
    val pieChartFragment = PieChartUserAnswers()
    val radarChartFragment = RadarChartUserAnswers()
    val bundle = Bundle()
    bundle.putSerializable(ANALYTICS_BUNDLE, analyticsModel)
    pieChartFragment.arguments = bundle
    radarChartFragment.arguments = bundle

    analyticsAdapter.addFragment(pieChartFragment)
    analyticsAdapter.addFragment(radarChartFragment)

    vp_analytics_charts.adapter = analyticsAdapter


    println("Model: $analyticsModel")

    bindButtons()
  }

  private fun bindButtons() {
    updateButtonsVisibility()
    ib_previous_chart.setOnClickListener { goToPreviousChart() }
    ib_next_chart.setOnClickListener { goToNextChart() }
  }

  private fun updateButtonsVisibility() {
    ib_previous_chart.visibility = when {
      vp_analytics_charts.currentItem <= 0 -> GONE
      else -> VISIBLE
    }

    ib_next_chart.visibility = when {
      vp_analytics_charts.currentItem >= vp_analytics_charts.adapter!!.count - 1 -> GONE
      else -> VISIBLE
    }

  }

  private fun goToNextChart() {
    vp_analytics_charts.currentItem = when {
      vp_analytics_charts.currentItem < vp_analytics_charts.adapter!!.count -> vp_analytics_charts.currentItem + 1
      else -> vp_analytics_charts.currentItem
    }
    updateButtonsVisibility()
  }

  private fun goToPreviousChart() {
    vp_analytics_charts.currentItem = when {
      vp_analytics_charts.currentItem > 0 -> vp_analytics_charts.currentItem - 1
      else -> vp_analytics_charts.currentItem
    }
    updateButtonsVisibility()
  }

  override fun showProgress() {
    pb_profile_analytics.visibility = VISIBLE
  }

  override fun hideProgress() {
    pb_profile_analytics.visibility = GONE
  }

  override fun populateCharts(analyticsModel: AnalyticsModel) {
    bindChartsViewPager(analyticsModel)
  }

  override fun showErrorMessage(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
  }
}
