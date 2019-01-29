package br.com.questv.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.questv.R
import br.com.questv.model.series.SeriesListAdapter

class HomeFragment : Fragment(), HomeView {

  private lateinit var recyclerView: RecyclerView

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    super.onCreateView(inflater, container, savedInstanceState)
    val view = inflater.inflate(R.layout.activity_home, container, false)
    this.recyclerView = view.findViewById(R.id.rv_home)
    initRecyclerView()
    return view
  }

  private fun initRecyclerView() {
    this.recyclerView.layoutManager = (LinearLayoutManager(context))
    this.recyclerView.adapter = SeriesListAdapter()
  }
}
