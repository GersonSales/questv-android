package br.com.questv.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.questv.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

  private val presenter = MainPresenter(this, MainInteractor())
  private lateinit var tabAdapter: TabAdapter;

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    this.tabAdapter = presenter.getTabAdapter(this, supportFragmentManager)

    setupTabView()
  }

  private fun setupTabView() {
    vp_main_pager.adapter = tabAdapter
    vp_main_pager.addOnPageChangeListener(presenter.getOnPageChangeListener())
    tl_main_swiper.setupWithViewPager(vp_main_pager)

    drawItemsTabIcon()
    highLightCurrentItem(0)
  }

  private fun drawItemsTabIcon() {
    (0..tl_main_swiper.tabCount).forEach { i ->
      tl_main_swiper.getTabAt(i)?.customView = null
      tl_main_swiper.getTabAt(i)?.customView = tabAdapter.getTabItemView(i)
    }
  }

  override fun onDestroy() {
    presenter.onDestroy()
    super.onDestroy()
  }

  override fun highLightCurrentItem(position: Int) {
    drawItemsTabIcon()
    tl_main_swiper.getTabAt(position)?.customView = null
    tl_main_swiper.getTabAt(position)?.customView = tabAdapter.getSelectedTabItemView(position)
  }
}
