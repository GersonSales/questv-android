package br.com.questv.ui.main

import android.support.v4.app.FragmentManager
import br.com.questv.ui.home.HomeFragment
import br.com.questv.ui.main.fragments.Tab1
import br.com.questv.ui.main.fragments.Tab2
import br.com.questv.ui.main.fragments.Tab3

class MainPresenter(var mainView: MainView?, private val mainInteractor: MainInteractor) {


  fun onDestroy() {
    mainView = null
  }


  fun getTabAdapter(fragmentManager: FragmentManager) : TabAdapter{
    val tabAdapter = TabAdapter(fragmentManager)

    tabAdapter.addFragment(HomeFragment(), "home")
    tabAdapter.addFragment(Tab1(), "Tab 1")
    tabAdapter.addFragment(Tab2(), "Tab 2")
    tabAdapter.addFragment(Tab3(), "Tab 3")


    return tabAdapter
  }
}