package br.com.questv.ui.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class TabAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {

  private val mFragmentList = mutableListOf<Fragment>()
  private val mTitleList = mutableListOf<String>()

  override fun getItem(i: Int): Fragment = mFragmentList.toList()[i]

  override fun getCount(): Int = mFragmentList.size

  override fun getPageTitle(position: Int): CharSequence? = mTitleList[position]

  fun addFragment(fragment: Fragment, title: String) {
    mFragmentList.add(fragment)
    mTitleList.add(title)
  }

}