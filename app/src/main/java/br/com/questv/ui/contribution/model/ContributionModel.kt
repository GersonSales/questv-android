package br.com.questv.ui.contribution.model

class ContributionModel(
  val description: String,
  private val answer1: ContributionAnswerModel,
  private val answer2: ContributionAnswerModel,
  private val answer3: ContributionAnswerModel,
  private val answer4: ContributionAnswerModel
) {

  private fun hasCorrectAnswer() = answer1.isCorrect || answer2.isCorrect || answer3.isCorrect || answer4.isCorrect
  private fun hasWrongAnswer() = !answer1.isCorrect || !answer2.isCorrect || !answer3.isCorrect || !answer4.isCorrect


  fun isValid() = description.trim().length >= 10
      && answer1.isValid()
      && answer2.isValid()
      && answer3.isValid()
      && answer4.isValid()
      && hasCorrectAnswer()
      && hasWrongAnswer()


  fun getInvalidCauseMessage(): String {
    if (!isValid()) {
      if (description.trim().length < 10) {
        return "Description must have 10 characters at least."
      }

      if (!answer1.isValid()) {
        return answer1.getInvalidCauseMessage()
      }

      if (!answer2.isValid()) {
        return answer2.getInvalidCauseMessage()
      }

      if (!answer3.isValid()) {
        return answer3.getInvalidCauseMessage()
      }

      if (!answer4.isValid()) {
        return answer4.getInvalidCauseMessage()
      }

      if (!hasCorrectAnswer()) {
        return "Must have one correct answer at least."
      }

      if (!hasWrongAnswer()) {
        return "Must have one wrong answer at least."
      }
    }
    return ""
  }


}