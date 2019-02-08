package br.com.questv.model.question.answer

import java.io.Serializable

class AnswerModel(
  val id: Long,
  val number: Int,
  val description: String,
  val isCorrect: Boolean
) : Serializable {

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as AnswerModel

    if (id != other.id) return false
    if (number != other.number) return false
    if (description != other.description) return false
    if (isCorrect != other.isCorrect) return false

    return true
  }

  override fun hashCode(): Int {
    var result = id.hashCode()
    result = 31 * result + number
    result = 31 * result + description.hashCode()
    result = 31 * result + isCorrect.hashCode()
    return result
  }

  override fun toString(): String {
    return "Answer = {id: $id, number: $number, description: $description, isCorrect: $isCorrect }"
  }
}




