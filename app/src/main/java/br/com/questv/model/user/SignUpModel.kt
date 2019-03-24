package br.com.questv.model.user

import com.google.gson.annotations.SerializedName

class SignUpModel(
  @SerializedName("firstName") val firstName: String,
  @SerializedName("lastName") val lastName: String,
  @SerializedName("username") val username: String,
  @SerializedName("email") val email: String,
  @SerializedName("password") val password: String
) {
}