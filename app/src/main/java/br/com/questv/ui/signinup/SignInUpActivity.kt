package br.com.questv.ui.signinup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.questv.R
import br.com.questv.model.user.UserLocalStorage
import br.com.questv.ui.main.MainActivity

class SignInUpActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_sign_in_up)

    if (UserLocalStorage(this).isLoggedIn()) {
      startActivity(Intent(this, MainActivity::class.java))
      finish()
    }
  }
}
