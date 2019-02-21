package br.com.questv.ui.main.fragments

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.questv.R
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter


class Tab2 : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.tab_two, container, false)
    val radarChar: RadarChart = view.findViewById(R.id.radar_chart)
    radarChar.webLineWidth = 1f
    radarChar.webColor = Color.LTGRAY
    radarChar.webLineWidthInner = 1f
    radarChar.webColorInner = Color.LTGRAY
    radarChar.webAlpha = 100


    val entries: MutableList<RadarEntry> = mutableListOf()

    entries.add(RadarEntry(100f, "UM"))
    entries.add(RadarEntry(90f, "DOIS"))
    entries.add(RadarEntry(80f, "TRES"))
    entries.add(RadarEntry(95f, "QUATRO"))
    entries.add(RadarEntry(94f, "CINCO"))

    val dataSet = RadarDataSet(entries, "String")
    dataSet.valueTextSize = 32f
    dataSet.color = Color.rgb(121, 162, 175)

    dataSet.setDrawFilled(true)
    dataSet.fillAlpha = 180
    dataSet.lineWidth = 2f
    dataSet.isDrawHighlightCircleEnabled = true
    dataSet.setDrawHighlightIndicators(false)



    radarChar.animateXY(1400, 1400, Easing.EaseInOutQuad)

    val xAxis = radarChar.xAxis
    xAxis.textSize = 9f
    xAxis.yOffset = 0f
    xAxis.xOffset = 0f
    xAxis.valueFormatter = object : IAxisValueFormatter{

      private val mActivities = arrayOf("Burger", "Steak", "Salad", "Pasta", "Pizza")

      override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        return mActivities[value.toInt() % mActivities.size]
      }
    }
    xAxis.textColor = Color.WHITE

    val yAxis = radarChar.yAxis
    yAxis.setLabelCount(5, false)
    yAxis.textSize = 9f
    yAxis.axisMinimum = 0f
    yAxis.axisMaximum = 80f
    yAxis.setDrawLabels(false)

    val l = radarChar.legend
    l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
    l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
    l.orientation = Legend.LegendOrientation.HORIZONTAL
    l.setDrawInside(false)
    l.xEntrySpace = 7f
    l.yEntrySpace = 5f
    l.textColor = Color.WHITE





    val colors = mutableListOf<Int>()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      colors.add(context?.getColor(br.com.questv.R.color.colorGreen)!!)
      colors.add(context?.getColor(br.com.questv.R.color.colorRed)!!)
      dataSet.fillColor = context?.getColor(br.com.questv.R.color.colorRed)!!
      dataSet.colors = colors
    }

    val data = RadarData(dataSet)

data.setValueTextSize(24f)
    radarChar.data = data
    radarChar.invalidate()


    return view
  }
}

