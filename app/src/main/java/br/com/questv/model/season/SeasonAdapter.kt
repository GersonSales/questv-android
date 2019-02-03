package br.com.questv.model.season

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.questv.R

class SeasonAdapter(private val seasonList: ArrayList<SeasonModel>) :
  RecyclerView.Adapter<SeasonViewHolder>() {

  override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): SeasonViewHolder {
    val inflater: LayoutInflater = LayoutInflater.from(viewGroup.context)
    val view: View = inflateView(viewGroup, inflater)
    seasonList.sortBy { seasonModel -> seasonModel.number }
    return SeasonViewHolder(view)
  }

  private fun inflateView(viewGroup: ViewGroup, inflater: LayoutInflater) =
    inflater.inflate(R.layout.season_row, viewGroup, false)

  override fun getItemCount() = seasonList.size

  override fun onBindViewHolder(seasonViewHolder: SeasonViewHolder, position: Int) {
    val seasonModel = seasonList[position]
    seasonViewHolder.itemView.setOnClickListener {
      seasonModel.isExpanded = !seasonModel.isExpanded
      notifyItemChanged(position)
    }
    seasonViewHolder.bind(seasonModel)
  }
}