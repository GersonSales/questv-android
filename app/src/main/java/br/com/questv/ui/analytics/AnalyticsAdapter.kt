package br.com.questv.ui.analytics

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class AnalyticsAdapter(private val context: Context,
                       fragmentManager: FragmentManager
):

  FragmentStatePagerAdapter(fragmentManager) {

  private val mFragmentList = mutableListOf<Fragment>()


  override fun getItem(position: Int) = mFragmentList[getRealPosition(position)]

  private fun getRealPosition(position: Int) = position%mFragmentList.size


  override fun getCount() = 1000


  fun addFragment(fragment: Fragment) {
    mFragmentList.add(fragment)
  }
}