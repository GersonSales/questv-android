package br.com.questv.ui.series

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.questv.R

class SeriesActivity : Fragment() {

  override fun onCreateView(inflater: LayoutInflater,
                            container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    inflater.inflate(R.layout.fragment_series, container, false);
    return super.onCreateView(inflater, container, savedInstanceState)
  }
}
