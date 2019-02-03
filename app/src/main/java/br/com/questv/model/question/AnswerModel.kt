package br.com.questv.model.question

import java.io.Serializable

class AnswerModel(val description: String,
                  val isCorrect: Boolean) : Serializable