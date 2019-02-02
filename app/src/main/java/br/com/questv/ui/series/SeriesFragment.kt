package br.com.questv.ui.series

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.questv.R
import br.com.questv.model.series.SeriesModel
import br.com.questv.resource.Strings.SERIES_KEY

class SeriesFragment() : Fragment() {

  override fun onCreateView(inflater: LayoutInflater,
                            container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {


    val view = inflater.inflate(R.layout.fragment_series, container, false)
    val seriesName: TextView  = view.findViewById(R.id.tv_series_detail_name);

    val seriesModel: SeriesModel = arguments?.get(SERIES_KEY) as SeriesModel

    seriesName.text = seriesModel.name

    return view
  }
}
