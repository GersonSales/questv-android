package br.com.questv.ui.series

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import br.com.questv.R
import br.com.questv.model.series.SeriesModel
import br.com.questv.resource.Strings
import com.nostra13.universalimageloader.core.ImageLoader

class SeriesBgFragment : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View {
     val view: View = inflater.inflate(R.layout.fragment_series, container, false)
    initView(view)
    val seriesFragment = SeriesFragment()
    seriesFragment.arguments = arguments
    fragmentManager?.beginTransaction()
      ?.replace(R.id.fl_series_screen, seriesFragment)
      ?.commit()
    return view
  }

  private fun initView(view: View) {
    val seriesModel: SeriesModel = arguments?.get(Strings.SERIES_KEY) as SeriesModel
    val seriesPromo: ImageView = view.findViewById(R.id.iv_series_details_promo)
    val imageLoader = ImageLoader.getInstance()
    val imageUrl = when {
      !seriesModel.promoImageUrl!!.isEmpty() -> seriesModel.promoImageUrl
      else -> seriesModel.coverImageUrl
    }

    imageLoader.displayImage(imageUrl!!.replace("localhost", "10.0.2.2"), seriesPromo)
    seriesPromo.scaleType = ImageView.ScaleType.CENTER_CROP
  }
}