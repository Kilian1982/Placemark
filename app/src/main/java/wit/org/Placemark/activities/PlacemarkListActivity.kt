package wit.org.Placemark.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_placemark_list.*
import org.jetbrains.anko.intentFor
import wit.org.placemark.R
import wit.org.Placemark.main.MainApp
import org.jetbrains.anko.startActivityForResult
import wit.org.placemark.models.PlacemarkModel

class PlacemarkListActivity : AppCompatActivity(), PlacemarkListener {

  lateinit var app: MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_placemark_list)
    app = application as MainApp

    val layoutManager = LinearLayoutManager(this)
    recyclerView.layoutManager = layoutManager
    recyclerView.adapter = PlacemarkAdapter(app.placemarks.findAll(), this)

    toolbarMain.title = title
    setSupportActionBar(toolbarMain)



  }
  override fun onPlacemarkClick(placemark: PlacemarkModel) {
    startActivityForResult(intentFor<MainActivity>().putExtra("placemark_edit", placemark), 0)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return super.onCreateOptionsMenu(menu)
  }
  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.item_add -> startActivityForResult<MainActivity>(0)
      R.id.item_cancel -> { finish() }
    }
    return super.onOptionsItemSelected(item)
  }
  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    //recyclerView is a widget in activity_placemark_list.xml
    recyclerView.adapter?.notifyDataSetChanged()
    super.onActivityResult(requestCode, resultCode, data)
  }
}




