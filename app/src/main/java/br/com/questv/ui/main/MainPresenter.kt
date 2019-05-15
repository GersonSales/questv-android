package br.com.questv.ui.main

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import br.com.questv.R
import br.com.questv.ui.home.IntroducerFragment
import br.com.questv.ui.main.fragments.Tab3
import br.com.questv.ui.ranking.RankingFragment
import br.com.questv.ui.store.StoreFragment

class MainPresenter(var mainView: MainView?, private val mainInteractor: MainInteractor) {


  fun onDestroy() {
    mainView = null
  }

  lateinit var tabAdapter: TabAdapter;

  fun getTabAdapter(context: Context, fragmentManager: FragmentManager): TabAdapter {
    tabAdapter = TabAdapter(context, fragmentManager)

    tabAdapter.addFragment(IntroducerFragment(), "home", R.drawable.ic_home_black_24dp)
    tabAdapter.addFragment(RankingFragment(), "Ranking", R.drawable.ic_trophy_black_24dp)
    tabAdapter.addFragment(StoreFragment(), "Store", R.drawable.ic_store_mall_directory_black_24dp)


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