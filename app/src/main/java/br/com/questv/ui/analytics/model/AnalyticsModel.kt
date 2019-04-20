package br.com.questv.ui.analytics.model

import java.io.Serializable

class AnalyticsModel(private var totalOfAnsweredQuestions: Int,
                     var correctAnsweredQuestions: Int,
                     var wrongAnsweredQuestions: Int,
                     var answeredSeries: List<AnsweredItem>,
                     var answeredCategories: List<AnsweredItem>) : Serializable {

  override fun toString(): String {
    return "AnalyticsModel(totalOfAnsweredQuestions=$totalOfAnsweredQuestions, " +
        "correctAnsweredQuestions=$correctAnsweredQuestions, " +
        "wrongAnsweredQuestions=$wrongAnsweredQuestions, " +
        "answeredSeries=$answeredSeries, " +
        "answeredCategories=$answeredCategories)"
  }

  fun getAllAnsweredCategories() : List<String>{
    val result = ArrayList<String>()
    for (item in answeredCategories) {
      result.add(item.name)
    }
    return result
  }
}

