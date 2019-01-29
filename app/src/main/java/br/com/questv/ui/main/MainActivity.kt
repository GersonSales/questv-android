package br.com.questv.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.questv.R
import br.com.questv.ui.home.HomeFragment
import br.com.questv.ui.main.fragments.Tab1
import br.com.questv.ui.main.fragments.Tab2
import br.com.questv.ui.main.fragments.Tab3
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setupTabView()
  }

  private fun setupTabView() {
    val tabAdapter = TabAdapter(supportFragmentManager)

    tabAdapter.addFragment(HomeFragment(), "home")
    tabAdapter.addFragment(Tab1(), "tab 1")
    tabAdapter.addFragment(Tab2(), "tab 2")
    tabAdapter.addFragment(Tab3(), "tab 3")

    vp_main_pager.adapter = tabAdapter
    tl_main_swiper.setupWithViewPager(vp_main_pager)
  }

}
