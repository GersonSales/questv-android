package br.com.questv.ui.series

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.TextView
import br.com.questv.R
import br.com.questv.model.series.SeriesModel
import br.com.questv.resource.Strings.SERIES_KEY
import br.com.questv.ui.home.HomeFragment

class SeriesFragment : Fragment(), SeriesView{

  override fun onCreateView(inflater: LayoutInflater,
                            container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {


    val view = inflater.inflate(R.layout.fragment_series, container, false)
    val seriesName: TextView  = view.findViewById(R.id.tv_series_details_name)

    val seriesModel: SeriesModel = arguments?.get(SERIES_KEY) as SeriesModel

    seriesName.text = seriesModel.name
    setHasOptionsMenu(true)
    return view
  }


  override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
    inflater?.inflate(R.menu.menu_series_details, menu)
    super.onPrepareOptionsMenu(menu)
  }

  override fun navigateToHome() {
    fragmentManager?.beginTransaction()
      ?.remove(this)
      ?.setCustomAnimations(R.animator.fade_out, R.animator.fade_out, 0, 0)
      ?.replace(R.id.fm_main_frame2, HomeFragment())
      ?.commit()
  }


}
