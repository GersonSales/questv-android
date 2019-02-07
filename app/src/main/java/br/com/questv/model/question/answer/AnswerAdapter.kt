package br.com.questv.model.question.answer

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import br.com.questv.R

class AnswerAdapter(
  private val answersMap: Map<String, Boolean>,
  private val listener: AnswerViewHolder.OnAnsweredQuestionListener,
  private val isClickable: Boolean
) :
  RecyclerView.Adapter<AnswerViewHolder>() {

  private var answersList = mutableListOf<AnswerModel>()

  init {
    for ((position, answerDescription) in this.answersMap.keys.withIndex()) {
      val isCorrect = this.answersMap[answerDescription]
      this.answersList.add(AnswerModel(position + 1, answerDescription, isCorrect!!))
    }
  }


  override fun onBindViewHolder(answerViewHolder: AnswerViewHolder, position: Int) {
    answerViewHolder.bind(this.answersList[position], listener)
  }

  override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): AnswerViewHolder {
    val inflater: LayoutInflater = LayoutInflater.from(viewGroup.context)
    val view: View = inflater.inflate(R.layout.answer_row, viewGroup, false)
    view.isEnabled = isClickable
    return AnswerViewHolder(view)
  }





  override fun getItemCount() = this.answersMap.keys.size
}