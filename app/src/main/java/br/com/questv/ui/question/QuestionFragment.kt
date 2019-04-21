package br.com.questv.ui.question

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.questv.R
import br.com.questv.model.question.QuestionModel
import br.com.questv.model.question.answer.AnswerAdapter
import br.com.questv.model.question.answer.AnswerViewHolder
import br.com.questv.model.user.UserLocalStorage
import br.com.questv.resource.Strings
import br.com.questv.resource.Strings.QUESTION_KEY
import br.com.questv.ui.question.model.AnsweredQuestionModel
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

    return inflater.inflate(R.layout.fragment_question, container, false)
  }

  private fun initView() {
    tv_question_description.text = questionModel.description

    rv_question_answers.layoutManager = LinearLayoutManager(context)
    val answers = questionModel.answers

    rv_question_answers.adapter = AnswerAdapter(answers, this)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    initView()
    bindQuestionRateBehavior()
    bindQuestionDifficultyBehavior()
    super.onViewCreated(view, savedInstanceState)
  }

  private fun bindQuestionDifficultyBehavior() {
    val difficultText = this.questionModel.getDifficultText()
    val difficulty = this.questionModel.difficult.toString()
    tv_question_difficulty.text = difficultText
    et_question_difficulty.setText(difficulty)

    tv_question_difficulty.setOnClickListener {
      tv_question_difficulty.visibility = GONE
      et_question_difficulty.visibility = VISIBLE
    }

    et_question_difficulty.setOnKeyListener(object : View.OnKeyListener {
      override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if (et_question_difficulty.text.isEmpty()) {
          et_question_difficulty.error = "Must be between 0 and 10."
          return false
        }

        if (keyCode == KeyEvent.KEYCODE_ENTER) {
          val difficultyInput = et_question_difficulty.text.toString().toInt()
          val auth = UserLocalStorage(context!!).getLoggedUserToken()
          presenter.sendQuestionDifficulty("", questionModel, difficultyInput, auth!!)
          return true
        }
        return false
      }
    })
  }

  private fun bindQuestionRateBehavior() {
    val questionRate = String.format("%.1f", this.questionModel.rate).replace(",", ".")
    tv_question_rate.text = questionRate
    et_question_rate.setText(questionRate)


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
    rv_question_answers.isLayoutFrozen = true
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

  override fun onAnswered(answerId: Long) {
    val userId = UserLocalStorage(context!!).getLoggedUserInfo().id
    val auth = UserLocalStorage(context!!).getLoggedUserToken()

    presenter.attachAnsweredQuestion(userId, auth, AnsweredQuestionModel(questionModel.id, answerId))
  }

  override fun showRateText() {
    tv_question_rate.visibility = VISIBLE
    et_question_rate.visibility = GONE
  }


  override fun showDifficultyText() {
    tv_question_difficulty.visibility = VISIBLE
    et_question_difficulty.visibility = GONE
  }

  override fun setRateError(message: String) {
    et_question_rate.error = message
  }

  override fun showToast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
  }
}