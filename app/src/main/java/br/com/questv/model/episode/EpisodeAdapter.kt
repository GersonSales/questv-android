package br.com.questv.model.episode

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.questv.R

class EpisodeAdapter(private var episodeList: List<EpisodeModel>) :
  RecyclerView.Adapter<EpisodeViewHolder>() {

  init {
    episodeList = episodeList.sortedBy { episodeModel -> episodeModel.number }

  }

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