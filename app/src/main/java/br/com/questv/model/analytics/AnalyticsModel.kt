package br.com.questv.model.analytics

import java.io.Serializable

class AnalyticsModel(private var totalOfAnsweredQuestions: Int,
                     var correctAnsweredQuestions: Int,
                     var wrongAnsweredQuestions: Int,
                     private var answeredSeries: Any) : Serializable{
  override fun toString(): String {
    return "AnalyticsModel(totalOfAnsweredQuestions=$totalOfAnsweredQuestions, " +
        "correctAnsweredQuestions=$correctAnsweredQuestions, " +
        "wrongAnsweredQuestions=$wrongAnsweredQuestions, " +
        "answeredSeries=$answeredSeries)"
  }
}

