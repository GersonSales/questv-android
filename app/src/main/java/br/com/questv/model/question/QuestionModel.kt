package br.com.questv.model.question

import java.io.Serializable

class QuestionModel(
  val id: Long,
  val ownerId: Long,
  val description: String,
  val difficult: Long,
  val reward: Long,
  val answers: Map<String, Boolean>,
  var isAnswered: Boolean
) : Serializable {
  /**
   * Returns a string representation of the object.
   */
  override fun toString(): String {
    return "Question{id: $id, description: $description, isAnswered: $isAnswered}"
  }
}