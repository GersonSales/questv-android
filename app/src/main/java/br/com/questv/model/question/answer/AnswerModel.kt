package br.com.questv.model.question.answer

import java.io.Serializable

class AnswerModel(val number: Int,
                  val description: String,
                  val isCorrect: Boolean) : Serializable

