package br.com.questv.model.user

class UserModel(
  val id: String,
  val firstName: String,
  val lastName: String,
  val username: String,
  val email: String,
  val password: String,
  val answeredQuestions: Map<String, String>
) {
  fun asKeySet(): Map<String, String> {
    val result = mutableMapOf<String, String>()
    result["id"] = id
    result["firstName"] = firstName
    result["lastName"] = lastName
    result["username"] = username
    result["email"] = email
    result["password"] = password
    result["answeredQuestions"] = answeredQuestions.toString()
    return result
  }
}