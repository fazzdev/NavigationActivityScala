package fazzdev.navigationactivityscala

import android.os.Bundle
import android.view.{Menu, MenuItem}
import androidx.appcompat.app.{ActionBarDrawerToggle, AppCompatActivity}
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import fazzdev.android.RichActivity
import fazzdev.android.ViewExtensions._

class MainActivity extends AppCompatActivity with RichActivity with NavigationView.OnNavigationItemSelectedListener {
  private lazy val drawerLayout = findView[DrawerLayout](R.id.drawer_layout)

  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val toolbar = findView[Toolbar](R.id.toolbar)
    setSupportActionBar(toolbar)

    val floatingActionButton = findView[FloatingActionButton](R.id.fab)
    floatingActionButton.onClick(view =>
        Snackbar.make(view, "Replace with your own action", 0) //Snackbar.LENGTH_LONG)
        .setAction("Action", null)
        .show()
    )

    val toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
    drawerLayout.addDrawerListener(toggle)
    toggle.syncState()

    val navigationView = findView[NavigationView](R.id.nav_view)
    navigationView.setNavigationItemSelectedListener(this)
  }

  override def onBackPressed() {
    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
      drawerLayout.closeDrawer(GravityCompat.START)
    } else {
      super.onBackPressed()
    }
  }

  override def onCreateOptionsMenu(menu: Menu): Boolean = {
    getMenuInflater.inflate(R.menu.main, menu)
    true
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    if (item.getItemId == R.id.action_settings)
      true
    else
      super.onOptionsItemSelected(item)
  }

  def onNavigationItemSelected(item: MenuItem): Boolean = {
    item.getItemId match {
      case R.id.nav_camera =>
      case R.id.nav_gallery =>
      case R.id.nav_slideshow =>
      case R.id.nav_manage =>
      case R.id.nav_share =>
      case R.id.nav_send =>
      case _ =>
    }

    drawerLayout.closeDrawer(GravityCompat.START)
    true
  }
}
