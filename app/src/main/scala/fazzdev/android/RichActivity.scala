package fazzdev.android

import android.app.Activity
import android.os.AsyncTask
import android.view.View

import scala.concurrent.ExecutionContext

trait RichActivity extends Activity {
  implicit lazy val executionContext = ExecutionContext.fromExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

  implicit def toRunnable[T](action: => T): Runnable = new Runnable() {
    def run() = action
  }

  def findView[T <: View](id: Int): T = findViewById(id).asInstanceOf[T]
}
