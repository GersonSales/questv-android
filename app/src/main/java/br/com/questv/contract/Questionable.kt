package br.com.questv.contract

import java.io.Serializable

interface Questionable : Serializable {
  fun getId(): Long
  fun getPromoImageUrl(): String?
  fun getCoverImageUrl(): String?
}