package br.com.questv.ui.contribution.model

class ContributionAnswerModel(val answer: String, val isCorrect: Boolean) {
  fun isValid() = answer.trim().length >= 10
  fun getInvalidCauseMessage(): String {
    if (answer.trim().length < 10) {
      return "Answer must have 10 characters at least."
    }
    return ""
  }
}