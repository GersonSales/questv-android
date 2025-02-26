package br.com.questv.model.season

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.questv.R
import br.com.questv.endpoint.ApiClient
import br.com.questv.model.episode.EpisodeAdapter
import br.com.questv.model.episode.EpisodeModel
import br.com.questv.model.user.UserLocalStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeasonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  interface OnInteractionListener {
    fun onClickPlayListener(seasonModel : SeasonModel)
  }


  private lateinit var recyclerView: RecyclerView

  private lateinit var seasonModel: SeasonModel
  private val context = itemView.context

  fun bind(seasonModel: SeasonModel, listener : OnInteractionListener) {
    itemView.findViewById<TextView>(R.id.tv_season_number)?.text = seasonModel.number.toString()
    itemView.findViewById<TextView>(R.id.tv_season_name)?.text = seasonModel.name
    itemView.findViewById<ImageButton>(R.id.ib_season_play).setOnClickListener { listener.onClickPlayListener(seasonModel)}
    recyclerView = itemView.findViewById(R.id.rv_episode_list)
    this.seasonModel = seasonModel
    getAllEpisodesFromSeason(UserLocalStorage(context).getLoggedUserToken())
  }


  private fun getAllEpisodesFromSeason(auth: String) {
    val consumptionCall: Call<ArrayList<EpisodeModel>> =
      ApiClient.instance.getAllEpisodesBySeason(seasonModel.ownerId, seasonModel.number.toString(), auth)

    consumptionCall.enqueue(object : Callback<ArrayList<EpisodeModel>> {
      override fun onFailure(call: Call<ArrayList<EpisodeModel>>, t: Throwable) {
          t.printStackTrace()
      }

      override fun onResponse(call: Call<ArrayList<EpisodeModel>>,
                              response: Response<ArrayList<EpisodeModel>>) {
        response.body()?.let {
        showRecyclerView(it)
        }
      }
    })
  }

  private fun showRecyclerView(episodeList: List<EpisodeModel>) {
    if(seasonModel.isExpanded) {
      recyclerView.visibility = VISIBLE
      recyclerView.layoutManager = (LinearLayoutManager(context))
      recyclerView.adapter = EpisodeAdapter(episodeList)
    }else {
      recyclerView.visibility = GONE
    }
  }

}