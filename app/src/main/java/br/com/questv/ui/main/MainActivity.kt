package br.com.questv.ui.main

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import br.com.questv.R
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar.*

class MainActivity : AppCompatActivity(), MainView {

  private val presenter = MainPresenter(this, MainInteractor())
  private lateinit var tabAdapter: TabAdapter;

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    this.tabAdapter = presenter.getTabAdapter(this, supportFragmentManager)

    ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this))

    setupTabView()
    setupToolBar()
  }

  private fun setupToolBar() {
    setSupportActionBar(tb_main_bar)
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

  override fun onBackPressed() {
    super.onBackPressed()
  }
}
