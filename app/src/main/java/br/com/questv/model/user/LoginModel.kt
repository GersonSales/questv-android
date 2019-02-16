package br.com.questv.model.user

import com.google.gson.annotations.SerializedName

class LoginModel(@SerializedName("username") val username: String,
                 @SerializedName("password") val password:String)