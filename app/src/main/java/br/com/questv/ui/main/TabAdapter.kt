package br.com.questv.ui.main

import android.content.Context
import android.graphics.PorterDuff
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import br.com.questv.R

class TabAdapter(private val context: Context, fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

  private val mFragmentList = mutableListOf<Fragment>()
  private val mTitleList = mutableListOf<String>()
  private val mIconList = mutableListOf<Int>()

  override fun getItem(i: Int): Fragment = mFragmentList[i]

  override fun getCount(): Int = mFragmentList.size

  override fun getPageTitle(position: Int): CharSequence? = null


  fun addFragment(fragment: Fragment, title: String, iconResource: Int) {
    mFragmentList.add(fragment)
    mTitleList.add(title)
    mIconList.add(iconResource)
  }

  fun getTabItemView(position: Int): View {
    val inflater = LayoutInflater.from(context)
    val view = inflater.inflate(R.layout.item_tab, null, false);
    val imageView: ImageView = view.findViewById(R.id.iv_tab_item)
    val textView: TextView = view.findViewById(R.id.tv_tab_title)
    imageView.setImageResource(mIconList[position])
    textView.text = mTitleList[position]
    return view
  }

  fun getSelectedTabItemView(position: Int): View {
    val inflater = LayoutInflater.from(context)
    val view = inflater.inflate(R.layout.item_tab, null, false)
    val imageView: ImageView = view.findViewById(R.id.iv_tab_item)
    imageView.setImageResource(mIconList[position])
    imageView.setColorFilter(ContextCompat.getColor(context, R.color.colorAccent), PorterDuff.Mode.SRC_ATOP)
    val textView: TextView = view.findViewById(R.id.tv_tab_title);
    textView.text = mTitleList[position]
    textView.setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
    return view
  }


}