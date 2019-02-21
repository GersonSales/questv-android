package br.com.questv.ui.main.fragments

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.questv.R
import br.com.questv.util.chart.DecimalDataFormatter
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter


class Tab2 : Fragment() {

  private lateinit var radarChart: RadarChart

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.tab_two, container, false)
    radarChart = view.findViewById(R.id.radar_chart)
    radarChart.webLineWidth = 1f
    radarChart.webColor = Color.LTGRAY
    radarChart.webLineWidthInner = 1f
    radarChart.webColorInner = Color.LTGRAY
    radarChart.webAlpha = 100
    radarChart.description.isEnabled = false

    setData()



    radarChart.animateXY(1400, 1400, Easing.EaseInOutQuad)

    val xAxis = radarChart.xAxis
    xAxis.textSize = 18f
    xAxis.yOffset = 0f
    xAxis.xOffset = 0f
    xAxis.valueFormatter = object : IAxisValueFormatter {

      private val mActivities = arrayOf("Comedy", "Action", "Anime", "Terror", "Sy-fy")

      override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        return mActivities[value.toInt() % mActivities.size]
      }
    }
    xAxis.textColor = Color.WHITE

    val yAxis = radarChart.yAxis
    yAxis.setLabelCount(5, false)
    yAxis.textSize = 18f
    yAxis.axisMinimum = 0f
    yAxis.axisMaximum = 80f
    yAxis.setDrawLabels(false)

    val l = radarChart.legend
    l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
    l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
    l.orientation = Legend.LegendOrientation.HORIZONTAL
    l.setDrawInside(false)
    l.xEntrySpace = 7f
    l.yEntrySpace = 5f
    l.textColor = Color.WHITE
    l.textSize = 14f


    val colors = mutableListOf<Int>()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      colors.add(context?.getColor(br.com.questv.R.color.colorGreen)!!)
      colors.add(context?.getColor(br.com.questv.R.color.colorRed)!!)
    }

    return view
  }

  private fun setData() {

    val mul = 80f
    val min = 20f
    val cnt = 5

    val entries1: MutableList<RadarEntry> = mutableListOf()
    val entries2: MutableList<RadarEntry> = mutableListOf()

    // NOTE: The order of the entries when being added to the entries array determines their position around the center of
    // the chart.
    for (i in 0 until cnt) {
      val val1 = (Math.random() * mul).toFloat() + min
      entries1.add(RadarEntry(val1))

      val val2 = (Math.random() * mul).toFloat() + min
      entries2.add(RadarEntry(val2))
    }

    val set1 = RadarDataSet(entries1, "Correct")
    set1.color = Color.GREEN
    set1.fillColor = Color.GREEN
    set1.setDrawFilled(true)
    set1.fillAlpha = 180
    set1.lineWidth = 0f
    set1.isDrawHighlightCircleEnabled = true
    set1.setDrawHighlightIndicators(false)

    val set2 = RadarDataSet(entries2, "Wrong")
    set2.color = Color.RED
    set2.fillColor = Color.RED
    set2.setDrawFilled(true)
    set2.fillAlpha = 180
    set2.lineWidth = 0f
    set2.isDrawHighlightCircleEnabled = true
    set2.setDrawHighlightIndicators(false)

    val sets: MutableList<RadarDataSet> = mutableListOf()
    sets.add(set1)
    sets.add(set2)

    val data = RadarData(sets.toList())
    data.setValueTextSize(8f)
    data.setValueTextSize(18f)
    data.setValueFormatter(DecimalDataFormatter())
    data.setValueTextColor(Color.WHITE)

    radarChart.data = data
    radarChart.invalidate()
  }

}

