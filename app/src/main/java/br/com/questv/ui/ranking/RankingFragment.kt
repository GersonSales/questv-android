package br.com.questv.ui.ranking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.questv.R
import br.com.questv.contract.Rankable
import br.com.questv.model.user.UserLocalStorage
import br.com.questv.ui.ranking.resource.RankingAdapter
import kotlinx.android.synthetic.main.fragment_ranking.*

class RankingFragment : Fragment(), RankingView {

  private val presenter = RankingPresenter(this)

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ) = inflater.inflate(R.layout.fragment_ranking, container, false)!!


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val auth = UserLocalStorage(context!!).getLoggedUserToken()
    this.presenter.consumeUserRanking(auth!!)
  }

  override fun showProgress() {
    pb_ranking.visibility = VISIBLE
    rv_ranking.visibility = GONE
  }

  override fun hideProgress() {
    pb_ranking.visibility = GONE
    rv_ranking.visibility = VISIBLE
  }

  override fun initRecyclerView(list: List<Rankable>) {
    rv_ranking.layoutManager = LinearLayoutManager(context)
    rv_ranking.adapter = RankingAdapter(list)
  }

  override fun showToastMessage(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
  }

  override fun navigateToHome() {
    println("Navigating to home!")
  }
}