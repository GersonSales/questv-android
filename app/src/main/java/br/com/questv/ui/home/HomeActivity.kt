package br.com.questv.ui.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import br.com.questv.R
import br.com.questv.model.series.SeriesListAdapter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), HomeView {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)
    initRecyclerView()
  }

  private fun initRecyclerView() {
    rv_home.layoutManager = (LinearLayoutManager(this))
    rv_home.adapter = SeriesListAdapter()
  }
}
