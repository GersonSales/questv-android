package br.com.questv.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.questv.R
import com.github.mikephil.charting.charts.RadarChart

class Tab3: Fragment() {

  private lateinit var radarChart: RadarChart


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.tab_three, container, false)
  }


}