package br.com.questv.ui.ranking.resource

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.questv.R
import br.com.questv.contract.Rankable

class RankingAdapter(private val users: List<Rankable>) : RecyclerView.Adapter<RankingViewHolder>() {


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val view = inflater.inflate(R.layout.ranking_row, parent, false)
    return RankingViewHolder(view)
  }

  override fun getItemCount() = users.size

  override fun onBindViewHolder(
    holder: RankingViewHolder,
    position: Int
  ) {
    holder.bind(users[position], position)
  }
}