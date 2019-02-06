package br.com.questv.ui.question.manager

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import br.com.questv.R
import br.com.questv.contract.Questionable
import br.com.questv.model.question.QuestionModel
import br.com.questv.model.question.manager.QuestionManagerAdapter
import br.com.questv.resource.Strings
import br.com.questv.resource.Strings.*
import br.com.questv.ui.ScoreFragment
import br.com.questv.ui.series.SeriesFragment
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

    presenter.fetchAllQuestions(questionable.getId())

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
  }

  private fun initNavigatorButtons(view: View) {
    val previousQuestionButton: ImageButton = view.findViewById(R.id.ib_previous_question)
    previousQuestionButton.setOnClickListener { navigateToPreviousQuestion(vp_question_swapper.currentItem) }

    val nextQuestionButton: ImageButton = view.findViewById(R.id.ib_next_question)
    nextQuestionButton.setOnClickListener { navigateToNextQuestion(vp_question_swapper.currentItem) }
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

  override fun navigateToPreviousQuestion(currentIndex: Int) {
    val previous = currentIndex - 1
    if (isAValidIndex(previous)) {
      navigateToIndexDelayed(previous)
    } else {
      navigateToItsCaller()
    }
  }

  override fun navigateToNextQuestion(currentIndex: Int) {
    val nextIndex = currentIndex + 1
    if (isAValidIndex(nextIndex)) {
      navigateToIndexDelayed(nextIndex)
    } else {
      navigateToScore()
    }
  }

  private fun navigateToIndexDelayed(index: Int) {
    Handler().postDelayed(
      object : Thread() {
        override fun run() {
          vp_question_swapper.currentItem = index
        }
      }, 0
    )
  }

  override fun navigateToScore() {
    val scoreFragment = ScoreFragment()


    fragmentManager
      ?.beginTransaction()
      ?.setCustomAnimations(R.animator.fade_in, R.animator.fade_out, 0, 0)
      ?.replace(R.id.fl_main_frame2, scoreFragment)
      ?.commit()

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


  override fun onAttach(context: Context?) {
    println("QuestionManagerFragment: onAttach")
    super.onAttach(context)
  }

  override fun onAttach(activity: Activity?) {
    println("QuestionManagerFragment: onAttach")
    super.onAttach(activity)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    println("QuestionManagerFragment: onActivityCreated")
    super.onActivityCreated(savedInstanceState)
  }

  override fun onStart() {
    println("QuestionManagerFragment: onStart")
    super.onStart()
  }

  override fun onResume() {
    println("QuestionManagerFragment: onResume")
    super.onResume()
  }

  override fun onPause() {
    println("QuestionManagerFragment: onPause")
    super.onPause()
  }

  override fun onStop() {
    println("QuestionManagerFragment: onStop")
    super.onStop()
  }

  override fun onDestroyView() {
    println("QuestionManagerFragment: onDestroyView")
    super.onDestroyView()
  }

  override fun onDestroy() {
    println("QuestionManagerFragment: onDestroy")
    super.onDestroy()
  }

  override fun onDetach() {
    println("QuestionManagerFragment: onDetach")
    super.onDetach()
  }

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    println("QuestionManagerFragment: onViewStateRestored")
    super.onViewStateRestored(savedInstanceState)
  }
}