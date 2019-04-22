package br.com.questv.ui.ranking.resource

import android.annotation.SuppressLint
import android.view.View
import android.view.View.GONE
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.questv.R
import br.com.questv.contract.Rankable

class RankingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


  @SuppressLint("ResourceType")
  fun bind(rankable: Rankable, rank: Int) {
    this.itemView.findViewById<TextView>(R.id.tv_ranking_username).text = rankable.getUsername()
    this.itemView.findViewById<TextView>(R.id.tv_ranking_score).text = rankable.getPoints().toString()

    val rankStar = this.itemView.findViewById<ImageView>(R.id.iv_ranking_star)
    when(rank) {
      0 -> rankStar.setImageResource(R.drawable.ic_star_gold_24dp)
      1 -> rankStar.setImageResource(R.drawable.ic_star_silver_24dp)
      2 -> rankStar.setImageResource(R.drawable.ic_star_bronze_24dp)
      else -> rankStar.visibility = GONE

    }



  }

}
