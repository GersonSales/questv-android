package br.com.questv.model.episode

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.questv.R

class EpisodeAdapter(private val episodeList: List<EpisodeModel>) :
  RecyclerView.Adapter<EpisodeViewHolder>() {

  override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): EpisodeViewHolder {
    val inflater: LayoutInflater = LayoutInflater.from(viewGroup.context)
    val view: View = inflateView(viewGroup, inflater)
    return EpisodeViewHolder(view)
  }

  private fun inflateView(viewGroup: ViewGroup, inflater: LayoutInflater)
      = inflater.inflate(R.layout.episode_row, viewGroup, false)

  override fun getItemCount() = episodeList.size

  override fun onBindViewHolder(episodeViewHolder: EpisodeViewHolder, position: Int) {
    episodeViewHolder.bind(this.episodeList[position])
  }
}