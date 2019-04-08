package br.com.questv.ui.question

import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.questv.R
import br.com.questv.model.question.QuestionModel
import br.com.questv.model.question.answer.AnswerAdapter
import br.com.questv.model.question.answer.AnswerViewHolder
import br.com.questv.model.user.UserLocalStorage
import br.com.questv.resource.Strings
import br.com.questv.resource.Strings.QUESTION_KEY
import kotlinx.android.synthetic.main.fragment_question.*


class QuestionFragment :
  Fragment(),
  AnswerViewHolder.OnAnsweredQuestionListener,
  QuestionView {
  interface OnAnswerListener {
    fun onCorrectAnswered(currentIndex: Int)
    fun onWrongAnswered(currentIndex: Int)
  }


  private lateinit var listener: OnAnswerListener
  private lateinit var questionModel: QuestionModel
  private lateinit var questionAnswers: RecyclerView
  private var positionOnManager = 0
  private val presenter = QuestionPresenter(this)


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    this.questionModel = arguments!!.get(QUESTION_KEY) as QuestionModel
    this.listener = arguments!!.get(Strings.QUESTION_MANAGER_FRAGMENT_KEY) as OnAnswerListener
    this.positionOnManager = arguments!!.getInt(Strings.QUESTION_FRAGMENT_POSITION)


    val view = inflater.inflate(R.layout.fragment_question, container, false)
    initView(this.questionModel, view)
    return view
  }

  private fun initView(questionModel: QuestionModel, view: View) {
    val questionDescription: TextView = view.findViewById(R.id.tv_question_description)
    questionDescription.text = questionModel.description

    questionAnswers = view.findViewById(R.id.rv_question_answers)
    questionAnswers.layoutManager = LinearLayoutManager(context)
    val answers = questionModel.answers

    questionAnswers.adapter = AnswerAdapter(answers, this)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    bindQuestionRateBehavior()




    super.onViewCreated(view, savedInstanceState)
  }

  private fun bindQuestionRateBehavior() {
    val questionRate = String.format("%.1f", this.questionModel.rate).replace(",", ".")
    tv_question_rate.text = questionRate
    et_question_rate.setText(questionRate)

    tv_question_difficulty.text = this.questionModel.getDifficultText()


    tv_question_rate.setOnClickListener {
      tv_question_rate.visibility = GONE
      et_question_rate.visibility = VISIBLE
    }

    et_question_rate.setOnKeyListener(object : View.OnKeyListener {
      override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
          val userInput = et_question_rate.text.toString()
          if (userInput.isEmpty()) {
            return false
          }

          val userRate = userInput.toDouble()
          val auth = UserLocalStorage(context!!).getLoggedUserToken()
          presenter.sendQuestionRate("", questionModel, userRate, auth!!)
          return true
        }
        return false
      }
    })
  }

  override fun onCorrectAnswer() {
    markThisAsAnswered()
    this.listener.onCorrectAnswered(this.positionOnManager)
  }

  private fun markThisAsAnswered() {
    this.questionModel.isAnswered = true
  }

  override fun onWrongAnswer() {
    questionAnswers.isLayoutFrozen = true
    markThisAsAnswered()
    this.listener.onWrongAnswered(this.positionOnManager)
  }

  private fun disableView() {
    val layout: ConstraintLayout = view!!.findViewById(R.id.cl_question_fragment)
    for (itemIndex in 0..layout.childCount) {
      val childAt = layout.getChildAt(itemIndex)
      println("Disabling $childAt")

      childAt?.isEnabled = false
    }
  }

  override fun showRateText() {
    tv_question_rate.visibility = VISIBLE
    et_question_rate.visibility = GONE
  }

  override fun setRateError(message: String) {
    et_question_rate.error = message
  }

  override fun showToast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
  }
}