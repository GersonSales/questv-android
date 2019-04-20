package br.com.questv.model.analytics

class AnalyticsModel(private var totalOfAnsweredQuestions: Int,
                     private var correctAnsweredQuestions: Int,
                     private var wrongAnsweredQuestions: Int,
                     private var answeredSeries: Any) {
  override fun toString(): String {
    return "AnalyticsModel(totalOfAnsweredQuestions=$totalOfAnsweredQuestions, " +
        "correctAnsweredQuestions=$correctAnsweredQuestions, " +
        "wrongAnsweredQuestions=$wrongAnsweredQuestions, " +
        "answeredSeries=$answeredSeries)"
  }
}

