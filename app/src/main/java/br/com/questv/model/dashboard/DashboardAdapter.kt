package br.com.questv.model.dashboard

import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.questv.R

class DashboardAdapter : RecyclerView.Adapter<DashboardViewHolder>() {

  val charts: MutableList<Fragment> = mutableListOf()


  override fun onCreateViewHolder(
    viewGroup: ViewGroup,
    position: Int
  ): DashboardViewHolder {
    val inflater = LayoutInflater.from(viewGroup.context)
    val view = inflateView(viewGroup, inflater)
    return DashboardViewHolder(view)

  }

  private fun inflateView(viewGroup: ViewGroup, inflater: LayoutInflater) =
    inflater.inflate(R.layout.dashboard_row, viewGroup, false)

  override fun getItemCount() = 1

  override fun onBindViewHolder(dashboardViewHolder: DashboardViewHolder, position: Int) {
    dashboardViewHolder.bind()
  }
}