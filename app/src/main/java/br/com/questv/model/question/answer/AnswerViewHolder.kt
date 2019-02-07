package br.com.questv.model.question.answer

import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import br.com.questv.R

class AnswerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  interface OnAnsweredQuestionListener {
    fun onCorrectAnswer()
    fun onWrongAnswer()
  }

  fun bind(answerModel: AnswerModel, listener: OnAnsweredQuestionListener) {
    itemView.findViewById<TextView>(R.id.tv_answer_item_number)!!.text = answerModel.number.toString()
    itemView.findViewById<TextView>(R.id.tv_question_answer_item)!!.text = answerModel.description
    val answerRow: ConstraintLayout = itemView.findViewById(R.id.cl_answer_row)
    answerRow.setOnClickListener {
      if (answerModel.isCorrect) {
        val colorCorrect = ContextCompat.getColor(itemView.context, R.color.colorGreen)
        answerRow.setBackgroundColor(colorCorrect)
        listener.onCorrectAnswer()

      } else {
        val colorWrong = ContextCompat.getColor(itemView.context, R.color.colorRed)
        answerRow.setBackgroundColor(colorWrong)
        listener.onWrongAnswer()

      }
    }
  }

}