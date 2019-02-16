package br.com.questv.model.user

class UserModel(
  private val id: String,
  private val firstName: String,
  private val lastName: String,
  private val username: String,
  private val email: String,
  private val password: String,
  private val answeredQuestions: Map<String, String>
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