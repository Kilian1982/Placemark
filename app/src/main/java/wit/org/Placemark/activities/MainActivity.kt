package wit.org.Placemark.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import wit.org.Placemark.main.MainApp
import wit.org.placemark.R
import wit.org.placemark.models.PlacemarkModel

class MainActivity : AppCompatActivity(), AnkoLogger{

  var placemarkTitle = PlacemarkModel()

  lateinit var app : MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    info("MainActivity Started")
    app = application as MainApp

    btnAdd.setOnClickListener {
      placemarkTitle.title = placemarkTitle.title.toString()
      placemarkTitle.description = description.text.toString()
      if (placemarkTitle.title.isNotEmpty()) {
        app.placemarks.add(placemarkTitle.copy())
        info("add Button Pressed: $placemarkTitle")
        app.placemarks.forEach {info("add Button Pressed: ${it}")}
      }
      else {
        toast ("Please Enter a title")
      }
    }
  }
}
