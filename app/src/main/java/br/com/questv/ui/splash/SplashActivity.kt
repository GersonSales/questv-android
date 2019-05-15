package br.com.questv.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.questv.R
import br.com.questv.ui.signinup.SignInUpActivity
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.utils.StorageUtils
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), SplashView {

  private val presenter = SplashPresenter(this, SplashInteractor())

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)

    ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this))
    presenter.fetchAllSeries()

  }



  override fun navigateToMainActivity() {
    Handler().postDelayed({
      startActivity(Intent(this@SplashActivity, SignInUpActivity::class.java))
      finish()
    }, 0)
  }

  override fun showToastMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
  }

  override fun showProgress() {
    pb_splash_progress.visibility = VISIBLE
  }

  override fun getContext(): Context? {
    return this
  }

  override fun navigateToErrorPage() {
    println("Navigate to error page")
  }

  override fun hideProgress() {
    pb_splash_progress.visibility = GONE
  }

  override fun navigateToUnavailableServicePage() {
    bt_conection_retry.visibility = VISIBLE
    bt_conection_retry.setOnClickListener {
      bt_conection_retry.visibility = GONE
      presenter.fetchAllSeries()
    }

  }
}
