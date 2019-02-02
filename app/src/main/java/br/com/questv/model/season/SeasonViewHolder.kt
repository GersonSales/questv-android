package br.com.questv.model.season

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import br.com.questv.R

class SeasonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  fun bind(seasonModel: SeasonModel) {
    itemView.findViewById<TextView>(R.id.tv_season_name)?.text = seasonModel.name
  }

}