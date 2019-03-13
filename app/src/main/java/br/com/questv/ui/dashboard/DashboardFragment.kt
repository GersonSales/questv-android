package br.com.questv.ui.dashboard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.questv.R
import br.com.questv.model.dashboard.DashboardAdapter

class DashboardFragment : Fragment(), DashboardView {

  private lateinit var recyclerView: RecyclerView
  private lateinit var presenter: DashboardPresenter

  override fun onCreateView(inflater: LayoutInflater,
                            container: ViewGroup?, savedInstanceState: Bundle?): View? {
    super.onCreateView(inflater, container, savedInstanceState)
    setHasOptionsMenu(true)
    val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
    this.recyclerView = view.findViewById(R.id.rv_dashboard)
    initRecyclerView()
    return view
  }

  private fun initRecyclerView() {
    this.recyclerView.layoutManager = LinearLayoutManager(context)
    this.recyclerView.adapter = DashboardAdapter()
  }


}