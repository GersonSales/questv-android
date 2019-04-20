package br.com.questv.ui.analytics.model

class AnsweredItem(var name: String,
                   var totalOfQuestions: Int,
                   var answeredQuestions: Int,
                   var correctAnsweredQuestions: Int,
                   var wrongAnsweredQuestions: Int) {

  override fun toString(): String {
    return "AnsweredItem(name=$name, " +
        "totalOfQuestions=$totalOfQuestions, " +
        "answeredQuestions=$answeredQuestions, " +
        "correctAnsweredQuestions=$correctAnsweredQuestions, " +
        "wrongAnsweredQuestions=$wrongAnsweredQuestions)"
  }
}