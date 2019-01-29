package br.com.questv.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.questv.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView{

  private val presenter = MainPresenter(this, MainInteractor())

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setupTabView()
  }

  private fun setupTabView() {
    vp_main_pager.adapter = presenter.getTabAdapter(supportFragmentManager)
    tl_main_swiper.setupWithViewPager(vp_main_pager)
  }

}
