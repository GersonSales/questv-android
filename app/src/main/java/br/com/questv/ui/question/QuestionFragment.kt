package br.com.questv.ui.question

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import br.com.questv.R
import br.com.questv.model.question.QuestionModel
import br.com.questv.model.question.answer.AnswerAdapter
import br.com.questv.model.question.answer.AnswerViewHolder
import br.com.questv.resource.Strings
import br.com.questv.resource.Strings.QUESTION_KEY

class QuestionFragment : Fragment(), AnswerViewHolder.OnAnsweredQuestionListener {
  interface OnQuestionInteractionListener {
    fun onCorrectAnswered(currentIndex: Int)
    fun onWrongAnswered(currentIndex: Int)
    fun onClickPreviousQuestion(currentIndex: Int)
    fun onClickNextQuestion(currentIndex: Int)
  }


  private lateinit var listener: OnQuestionInteractionListener
  private lateinit var questionModel: QuestionModel
  private lateinit var questionAnswers: RecyclerView
  private var positionOnManager = 0

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    this.questionModel = arguments!!.get(QUESTION_KEY) as QuestionModel
    this.listener = arguments!!.get(Strings.QUESTION_MANAGER_FRAGMENT_KEY) as OnQuestionInteractionListener
    this.positionOnManager = arguments!!.getInt(Strings.QUESTION_FRAGMENT_POSITION)


    val view = inflater.inflate(R.layout.fragment_question, container, false)
    initView(this.questionModel, view)
    return view
  }

  private fun initView(questionModel: QuestionModel, view: View) {
    println(questionModel)
    val questionDescription: TextView = view.findViewById(R.id.tv_question_description)
    questionDescription.text = questionModel.description

    questionAnswers = view.findViewById(R.id.rv_question_answers)
    questionAnswers.layoutManager = LinearLayoutManager(context)
    val isClickable = !questionModel.isAnswered
    questionAnswers.adapter = AnswerAdapter(questionModel.answers, this, isClickable )
  }

  override fun onCorrectAnswer() {
    markThisAsAnswered()
    this.listener.onCorrectAnswered(this.positionOnManager)
  }

  private fun markThisAsAnswered() {
    this.questionModel.isAnswered = true
  }

  override fun onWrongAnswer() {
    markThisAsAnswered()
    this.listener.onWrongAnswered(this.positionOnManager)
  }

  private fun disableView() {
    val layout: ConstraintLayout = view!!.findViewById(R.id.cl_question_fragment)
    for(itemIndex in 0..layout.childCount) {
      layout.getChildAt(itemIndex)?.isEnabled = false
    }
  }

}