package fazzdev.android

import android.app.Activity
import android.view.View

trait RichActivity extends Activity {
  implicit def toRunnable[T](action: => T): Runnable = new Runnable() {
    def run(): Unit = action
  }

  def findView[T <: View](id: Int): T = findViewById(id).asInstanceOf[T]
}
