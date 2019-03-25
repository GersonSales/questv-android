package br.com.questv.model.episode

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.questv.R

class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  private val episodeNumber: TextView = itemView.findViewById(R.id.tv_episode_number)
  private val episodeName: TextView = itemView.findViewById(R.id.tv_episode_name)

  fun bind(episodeModel: EpisodeModel) {
    episodeName.text = episodeModel.name
    episodeNumber.text = episodeModel.number.toString()
  }
}