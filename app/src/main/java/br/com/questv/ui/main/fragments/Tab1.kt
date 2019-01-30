package br.com.questv.ui.main.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.view.*
import br.com.questv.R

class Tab1: Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    setHasOptionsMenu(true)
    return inflater.inflate(R.layout.tab_one, container, false)
  }

  override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
    inflater?.inflate(R.menu.menu_main, menu)
    super.onPrepareOptionsMenu(menu)
  }
}