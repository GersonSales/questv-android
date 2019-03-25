package br.com.questv.ui.ranking.resource

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.questv.R
import br.com.questv.contract.Rankable

class RankingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


  fun bind(rankable: Rankable) {
    this.itemView.findViewById<TextView>(R.id.tv_ranking_username).text = rankable.getUsername()
  }

}
