package br.com.questv.ui.main.fragments

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.questv.R
import br.com.questv.model.analytics.AnalyticsModel
import br.com.questv.resource.Strings
import br.com.questv.util.chart.DecimalDataFormatter
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.android.synthetic.main.tab_one.*

class PieChartUserAnswers : Fragment() {

  private lateinit var analyticsModel: AnalyticsModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.tab_one, container, false)
    analyticsModel = arguments?.getSerializable(Strings.ANALYTICS_BUNDLE) as AnalyticsModel
    return view
  }


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    bindAnsweredQuestionsPie()
    super.onViewCreated(view, savedInstanceState)
  }

  private fun bindAnsweredQuestionsPie() {
    val entries: MutableList<PieEntry> = mutableListOf()

    val correctAnswered = this.analyticsModel.correctAnsweredQuestions.toFloat()
    entries.add(PieEntry(correctAnswered, "Correct"))

    val wrongAnswered = this.analyticsModel.wrongAnsweredQuestions.toFloat()
    entries.add(PieEntry(wrongAnswered, "Wrong"))

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

    pie_chart.data = data
    pie_chart.animateY(800, Easing.EaseOutSine)

    pie_chart.description.text = "Pie chart of answered questions"
    pie_chart.description.textSize = 10f
    pie_chart.description.textColor = Color.WHITE

    pie_chart.legend.textSize = 14f
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      pie_chart.setHoleColor(context?.getColor(R.color.colorGray)!!)
      pie_chart.legend.textColor = context?.getColor(R.color.colorWhite)!!
    }
    pie_chart.invalidate()
  }
}