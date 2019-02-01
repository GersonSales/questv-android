package br.com.questv.model.series

import android.net.Uri

class SeriesModel(
  val name: String,
  val abbreviation: String,
  var imageCoverUri: Uri?
) {
}