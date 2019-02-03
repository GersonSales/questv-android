package br.com.questv.ui.question

import android.os.Bundle
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
    fun onCorrectAnswered()
    fun onWrongAnswered()
    fun onClickPreviousQuestion()
    fun onClickNextQuestion()
  }


  private lateinit var listener: OnQuestionInteractionListener

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    val questionModel: QuestionModel = arguments!!.get(QUESTION_KEY) as QuestionModel
    this.listener = arguments!!.get(Strings.QUESTION_MANAGER_FRAGMENT_KEY) as OnQuestionInteractionListener

    val view = inflater.inflate(R.layout.fragment_question, container, false)
    initView(questionModel, view)
    return view
  }

  private fun initView(questionModel: QuestionModel, view: View) {
    val questionDescription: TextView = view.findViewById(R.id.tv_question_description)
    questionDescription.text = questionModel.description

    initNavigatorButtons(view)

    val questionAnswers: RecyclerView = view.findViewById(R.id.rv_question_answers)
    questionAnswers.layoutManager = LinearLayoutManager(context)
    questionAnswers.adapter = AnswerAdapter(questionModel.answers, this)
  }

  private fun initNavigatorButtons(view: View) {
    val previousQuestionButton: ImageButton = view.findViewById(R.id.ib_previous_question)
    previousQuestionButton.setOnClickListener { this.listener.onClickPreviousQuestion() }

    val nextQuestionButton: ImageButton = view.findViewById(R.id.ib_next_question)
    nextQuestionButton.setOnClickListener {this.listener.onClickNextQuestion()}
  }

  override fun onCorrectAnswer() {
    this.listener.onCorrectAnswered()
  }

  override fun onWrongAnswer() {
    this.listener.onWrongAnswered()
  }
}