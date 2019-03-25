package br.com.questv.ui.main.fragments

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.questv.R
import br.com.questv.util.chart.DecimalDataFormatter
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class Tab1 : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.tab_one, container, false)
    bindAnsweredQuestionsPie(view)

    return view
  }

  private fun bindAnsweredQuestionsPie(view: View) {
    val pieChart: PieChart = view.findViewById(R.id.pie_chart)

    val entries: MutableList<PieEntry> = mutableListOf()

    entries.add(PieEntry(26f, "Correct"))
    entries.add(PieEntry(12f, "Wrong"))

    val dataSet = PieDataSet(entries, "")
    dataSet.valueTextSize = 24f

    val colors = mutableListOf<Int>()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      colors.add(context?.getColor(R.color.colorGreen)!!)
      colors.add(context?.getColor(R.color.colorRed)!!)
      dataSet.colors = colors
    }


    val data = PieData(dataSet)
    data.setValueFormatter(DecimalDataFormatter())

    pieChart.data = data
    pieChart.animateY(800, Easing.EaseOutSine)

    pieChart.description.text = "Pie chart of answered questions"
    pieChart.description.textSize = 10f
    pieChart.description.textColor = Color.WHITE

    pieChart.legend.textSize = 14f
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      pieChart.setHoleColor(context?.getColor(R.color.colorGray)!!)
      pieChart.legend.textColor = context?.getColor(R.color.colorWhite)!!
    }
    pieChart.invalidate()
  }
}