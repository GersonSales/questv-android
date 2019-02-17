package br.com.questv.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation.findNavController
import br.com.questv.R
import br.com.questv.resource.Strings
import br.com.questv.ui.user.UserNavigationFragment
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import kotlinx.android.synthetic.main.activity_main.*

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
    if (!onSupportNavigateUp()) {
      super.onBackPressed()
    }
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return super.onPrepareOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when {
      item?.itemId == R.id.mi_user_menu -> navigateToUserAccount()
    }
    return super.onOptionsItemSelected(item)
  }


  private fun navigateToUserAccount() {
    supportFragmentManager?.beginTransaction()
      ?.setCustomAnimations(R.animator.slide_in_top, R.animator.slide_out_bottom, 0, 0)
      ?.addToBackStack(Strings.HOME_FRAGMENT_TAG)
      ?.replace(R.id.fl_main_frame, UserNavigationFragment())
      ?.commit()
  }


  override fun onSupportNavigateUp() = findNavController(findViewById(R.id.nv_fg_home)).navigateUp()

  override fun navigateToUserProfile() {
    println("navigateToUserProfile")
  }
}
