package br.com.questv.model.series

import android.net.Uri

class SeriesModel(val id: Long,
                  val name: String,
                  val abbreviation: String,
                  val category: String,
                  var coverImage: String,
                  var coverImageUri: Uri?,
                  val promoImage: String,
                  val seasons: List<Long>,
                  val questions: List<Long>)