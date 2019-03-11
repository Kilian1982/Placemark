package wit.org.Placemark.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import wit.org.Placemark.helpers.readImage
import wit.org.Placemark.helpers.readImageFromPath
import wit.org.Placemark.helpers.showImagePicker
import wit.org.Placemark.main.MainApp
import wit.org.placemark.R
import wit.org.placemark.models.PlacemarkModel

class MainActivity : AppCompatActivity(), AnkoLogger{

  var placemark = PlacemarkModel()

  lateinit var app : MainApp

  var edit = false

  val IMAGE_REQUEST = 1

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    info("MainActivity Started")
    toolbarAdd.title = title
    setSupportActionBar(toolbarAdd)
    app = application as MainApp

    if (intent.hasExtra("placemark_edit")) {
      edit = true
      placemark = intent.extras.getParcelable<PlacemarkModel>("placemark_edit")
      placemarkTitle.setText(placemark.title)
      description.setText(placemark.description)
      placemarkImage.setImageBitmap(readImageFromPath(this, placemark.image))
      if(placemark.image !=null){
        chooseImage.setText(R.string.change_placemark_image)
      }
      btnAdd.setText(R.string.save_placemark)
    }

    btnAdd.setOnClickListener() {
        placemark.title = placemarkTitle.text.toString()
        placemark.description = description.text.toString()
        if (placemark.title.isEmpty()) {
          toast(R.string.enter_placemark_title)
        } else {
          if (edit) {
            app.placemarks.update(placemark.copy())
          } else {
            app.placemarks.create(placemark.copy())
          }
        }
        info("add Button Pressed: $placemarkTitle")
        setResult(AppCompatActivity.RESULT_OK)
        finish()
      }
    chooseImage.setOnClickListener {
      showImagePicker(this, IMAGE_REQUEST)
    }
    placemarkLocation.setOnClickListener {
      info ("Set Location Pressed")
    }

  }
  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_placemark, menu)
    return super.onCreateOptionsMenu(menu)
  }
  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.item_cancel -> { finish() }
    }
    return super.onOptionsItemSelected(item)
  }
  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    when (requestCode) {
      IMAGE_REQUEST ->
      {
        if (data != null) {
          placemark.image = data.getData().toString()
          placemarkImage.setImageBitmap(readImage(this, resultCode, data))
          chooseImage.setText(R.string.change_placemark_image)
        }
      }
    }
  }

}
