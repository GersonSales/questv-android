package br.com.questv.ui.main

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import br.com.questv.R
import br.com.questv.ui.home.HomeFragment
import br.com.questv.ui.main.fragments.Tab1
import br.com.questv.ui.main.fragments.Tab2
import br.com.questv.ui.main.fragments.Tab3

class MainPresenter(var mainView: MainView?, private val mainInteractor: MainInteractor) {


  fun onDestroy() {
    mainView = null
  }


  fun getTabAdapter(context: Context, fragmentManager: FragmentManager): TabAdapter {
    val tabAdapter = TabAdapter(context, fragmentManager)

    tabAdapter.addFragment(HomeFragment(), "home", R.drawable.ic_home_black_24dp)
    tabAdapter.addFragment(Tab1(), "Dashboard", R.drawable.ic_show_chart_black_24dp)
    tabAdapter.addFragment(Tab2(), "Questions", R.drawable.ic_wb_incandescent_black_24dp)
    tabAdapter.addFragment(Tab3(), "Badges", R.drawable.ic_assistant_photo_black_24dp)


    return tabAdapter
  }

  fun getOnPageChangeListener(): ViewPager.OnPageChangeListener {
    return object : ViewPager.OnPageChangeListener {
      override fun onPageScrollStateChanged(p0: Int) {
      }

      override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
      }

      override fun onPageSelected(position: Int) {
        mainView?.highLightCurrentItem(position)
      }

    }
  }
}