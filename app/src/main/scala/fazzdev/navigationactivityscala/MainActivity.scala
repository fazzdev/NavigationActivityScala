package fazzdev.navigationactivityscala

import android.os.Bundle
import android.support.design.widget.{FloatingActionButton, NavigationView, Snackbar}
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.{ActionBarDrawerToggle, AppCompatActivity}
import android.support.v7.widget.Toolbar
import android.view.{Menu, MenuItem}
import fazzdev.android.RichActivity
import fazzdev.android.ViewExtensions._

class MainActivity extends AppCompatActivity with RichActivity with NavigationView.OnNavigationItemSelectedListener {
  lazy val drawerLayout = findView[DrawerLayout](R.id.drawer_layout)

  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val toolbar = findView[Toolbar](R.id.toolbar)
    setSupportActionBar(toolbar)

    val floatingActionButton = findView[FloatingActionButton](R.id.fab)
    floatingActionButton.onClick(view =>
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null)
        .show()
    )

    val toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
    drawerLayout.setDrawerListener(toggle)
    toggle.syncState()

    val navigationView = findView[NavigationView](R.id.nav_view)
    navigationView.setNavigationItemSelectedListener(this)
  }

  override def onBackPressed() {
    drawerLayout.isDrawerOpen(GravityCompat.START) match {
      case true => drawerLayout.closeDrawer(GravityCompat.START)
      case _ => super.onBackPressed()
    }
  }

  override def onCreateOptionsMenu(menu: Menu) = {
    getMenuInflater.inflate(R.menu.main, menu)
    true
  }

  override def onOptionsItemSelected(item: MenuItem) = {
    if (item.getItemId == R.id.action_settings)
      true
    else
      super.onOptionsItemSelected(item)
  }

  def onNavigationItemSelected(item: MenuItem) = {
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
