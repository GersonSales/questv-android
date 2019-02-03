package br.com.questv.model.episode

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import br.com.questv.R

class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  private val episodeName: TextView = itemView.findViewById(R.id.tv_episode_name)

  fun bind(episodeModel: EpisodeModel) {
    episodeName.text = episodeModel.name
  }
}