package br.com.questv.model.series.dto

class SeriesDTO(val id: Long,
                val name: String,
                val abbreviation: String,
                val category: String,
                val coverImage: String,
                val promoImage: String,
                val seasons: List<Long>,
                val questions: List<Long>) {



}