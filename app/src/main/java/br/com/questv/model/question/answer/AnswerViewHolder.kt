package br.com.questv.model.question.answer

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import br.com.questv.R

class AnswerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  fun bind(answerModel: AnswerModel) {
    itemView.findViewById<TextView>(R.id.tv_answer_item_number)!!.text = answerModel.number.toString()
    itemView.findViewById<TextView>(R.id.tv_question_answer_item)!!.text = answerModel.description
    val answerRow: ConstraintLayout = itemView.findViewById(R.id.cl_answer_row)
    answerRow.setOnClickListener {
      val colorCorrect = ContextCompat.getColor(itemView.context, R.color.colorGreen)
      val colorWrong = ContextCompat.getColor(itemView.context, R.color.colorRed)
      answerRow.setBackgroundColor(if (answerModel.isCorrect) colorCorrect else colorWrong)

    }
  }

}