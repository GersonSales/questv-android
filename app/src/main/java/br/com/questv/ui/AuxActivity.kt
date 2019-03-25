package br.com.questv.ui

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import br.com.questv.R
import kotlinx.android.synthetic.main.activity_aux.*

class AuxActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_aux)
    setupToolBar()
  }

  private fun setupToolBar() {
    setSupportActionBar(tb_aux_bar)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_home, menu)
    return true
  }

}
