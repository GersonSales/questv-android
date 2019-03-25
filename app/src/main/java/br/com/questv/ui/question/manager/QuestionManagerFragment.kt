package br.com.questv.ui.question.manager

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import br.com.questv.R
import br.com.questv.contract.Questionable
import br.com.questv.model.question.QuestionModel
import br.com.questv.model.question.manager.QuestionManagerAdapter
import br.com.questv.model.user.TempUser
import br.com.questv.model.user.UserLocalStorage
import br.com.questv.resource.Strings
import br.com.questv.resource.Strings.CORRECT_ANSWERED_QUESTIONS
import br.com.questv.resource.Strings.QUESTIONABLE_ID
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.fragment_question_manager.*

class QuestionManagerFragment : Fragment(), QuestionManagerView {

  private val presenter = QuestionManagerPresenter(this, QuestionManagerInteractor())
  private lateinit var questionManagerAdapter: QuestionManagerAdapter

  private lateinit var questionable: Questionable

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    return inflater.inflate(R.layout.fragment_question_manager, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    questionable = arguments!!.get(QUESTIONABLE_ID) as Questionable

    initViewImageView(view)
    initNavigatorButtons(view)

    val auth = UserLocalStorage(context!!).getLoggedUserToken()

    presenter.fetchAllQuestions(questionable.getId(), auth!!)

    super.onViewCreated(view, savedInstanceState)
  }

  private fun initViewImageView(view: View) {
    val seriesPromo: ImageView = view.findViewById(R.id.iv_series_details_promo3)
    val imageLoader = ImageLoader.getInstance()
    val imageUrl = when {
      !questionable.getPromoImageUrl()!!.isEmpty() -> questionable.getPromoImageUrl()
      else -> questionable.getCoverImageUrl()
    }

    imageLoader.displayImage(imageUrl!!.replace("localhost", "10.0.2.2"), seriesPromo)
    seriesPromo.scaleType = ImageView.ScaleType.CENTER_CROP
  }

  override fun initViewPager(questions: List<QuestionModel>) {
    questionManagerAdapter = QuestionManagerAdapter(questions, fragmentManager!!, this)
    vp_question_swapper.adapter = questionManagerAdapter
    updateCounter()
  }

  private fun updateCounter() {
    tv_current_question.text = (vp_question_swapper.currentItem + 1).toString()
    tv_questions_count.text = questionManagerAdapter.count.toString()
  }

  private fun initNavigatorButtons(view: View) {
    val previousQuestionButton: ImageButton = view.findViewById(R.id.ib_previous_question)
    previousQuestionButton.setOnClickListener { navigateToPreviousQuestion(vp_question_swapper.currentItem, false) }

    val nextQuestionButton: ImageButton = view.findViewById(R.id.ib_next_question)
    nextQuestionButton.setOnClickListener { navigateToNextQuestion(vp_question_swapper.currentItem, false) }
  }

  override fun showProgress() {
    print("Loading...")
  }

  override fun hideProgress() {
    print("Stop Loading...")
  }

  override fun showErrorMessage(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
  }

  override fun navigateToPreviousQuestion(currentIndex: Int, delayed: Boolean) {
    val previous = currentIndex - 1
    if (isAValidIndex(previous)) {
      navigateToIndexDelayed(previous, delayed)
    } else {
      navigateToItsCaller()
    }
  }

  override fun navigateToNextQuestion(currentIndex: Int, delayed: Boolean) {
    val nextIndex = currentIndex + 1
    if (isAValidIndex(nextIndex)) {
      navigateToIndexDelayed(nextIndex, delayed)
    } else {
      navigateToScore()
    }
  }

  private fun navigateToIndexDelayed(index: Int, delay: Boolean) {
    disableInteraction()
    Handler().postDelayed(
      object : Thread() {
        override fun run() {
          vp_question_swapper.currentItem = index
          updateCounter()
          enableInteraction()
        }
      }, if (delay) 1000 else 0L
    )
  }

  override fun navigateToScore() {
    val bundle = Bundle()
    bundle.putInt(CORRECT_ANSWERED_QUESTIONS, TempUser.getCorrectAnsweredCount())
    bundle.putInt(Strings.TOTAL_OF_QUESTIONS, questionManagerAdapter.count)


    NavHostFragment.findNavController(this)
      .navigate(
        R.id.scoreFragment, bundle, NavOptions.Builder()
          .setPopUpTo(
            R.id.questionManagerFragment,
            true
          ).build()
      )
  }

  override fun navigateToItsCaller() {
    fragmentManager?.popBackStack()
  }

  override fun disableCurrentQuestion(currentIndex: Int) {
  }


  private fun isAValidIndex(index: Int): Boolean {
    val count = this.questionManagerAdapter.count
    return count > 0
        && (index in 0..(count - 1))
  }

  private fun disableInteraction() {
    activity!!.window.setFlags(
      WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
      WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
    )
  }

  private fun enableInteraction() {
    activity!!.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
  }
}