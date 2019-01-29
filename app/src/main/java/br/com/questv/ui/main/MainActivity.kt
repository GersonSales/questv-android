package br.com.questv.ui.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import br.com.questv.R
import br.com.questv.ui.home.HomeFragment
import br.com.questv.ui.main.fragments.Tab1
import br.com.questv.ui.main.fragments.Tab2
import br.com.questv.ui.main.fragments.Tab3

class MainActivity : AppCompatActivity() {


  private lateinit var tabAdapter: TabAdapter
  private lateinit var tabLayout: TabLayout
  private lateinit var viewPager: ViewPager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    this.tabLayout = findViewById(R.id.tabLayout)
    this.viewPager = findViewById(R.id.viewPager)

    this.tabAdapter = TabAdapter(supportFragmentManager)

    this.tabAdapter.addFragment(HomeFragment(), "home")
    this.tabAdapter.addFragment(Tab1(), "tab 1")
    this.tabAdapter.addFragment(Tab2(), "tab 2")
    this.tabAdapter.addFragment(Tab3(), "tab 3")

    this.viewPager.adapter = this.tabAdapter
    this.tabLayout.setupWithViewPager(this.viewPager)
  }

}
