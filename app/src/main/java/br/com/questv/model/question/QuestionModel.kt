package br.com.questv.model.question

import br.com.questv.model.question.answer.AnswerModel
import java.io.Serializable

class QuestionModel(
  val id: String,
  val ownerId: String,
  val description: String,
  val difficult: Long,
  val reward: Long,
  private val _answers: Map<Long, Map<String, Boolean>>,
  var isAnswered: Boolean
) : Serializable {

  val answers: MutableList<AnswerModel>
    get() {
      val result: MutableList<AnswerModel> = mutableListOf()
      for ((position, id) in _answers.keys.withIndex()) {
        for (answer in _answers.getValue(id).keys) {
          val isCorrect = _answers.getValue(id)[answer]
          result.add(AnswerModel(id, position + 1, answer, isCorrect!!))
        }
      }
      return result
    }

  fun isDescriptionValid() = description.trim().length >= 10
  fun getInvalidDescriptionCauseMessage() = when {
    isDescriptionValid() -> ""
    else -> "Description must have 10 characters at least!"
  }


  /**
   * Returns a string representation of the object.
   */
  override fun toString(): String {
    return "Question{id: $id, description: $description, isAnswered: $isAnswered answers: $answers}"
  }
}