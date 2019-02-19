package wit.org.placemark.activities.MainActivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import wit.org.placemark.R
import wit.org.Placemark.main.MainApp

class PlacemarkListActivity : AppCompatActivity() {

  lateinit var app: MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_placemark_list)
    app = application as MainApp
  }
}