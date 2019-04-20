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


  override fun getItem(position: Int) = mFragmentList[position]

  override fun getCount() = mFragmentList.size

  fun addFragment(fragment: Fragment) {
    mFragmentList.add(fragment)
  }
}