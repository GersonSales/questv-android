package br.com.questv.model.question.answer

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.questv.R
import br.com.questv.model.user.TempUser

class AnswerAdapter(
  private val answers: List<AnswerModel>,
  private val listener: AnswerViewHolder.OnAnsweredQuestionListener
) :
  RecyclerView.Adapter<AnswerViewHolder>(){

  override fun onBindViewHolder(answerViewHolder: AnswerViewHolder, position: Int) {
    answerViewHolder.bind(this.answers[position], listener)
  }

  override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): AnswerViewHolder {
    val inflater: LayoutInflater = LayoutInflater.from(viewGroup.context)
    val view: View = inflater.inflate(R.layout.answer_row, viewGroup, false)
    view.isEnabled = !TempUser.hasAnswered(answers)
    return AnswerViewHolder(view)
  }

  override fun getItemCount() = this.answers.size

}