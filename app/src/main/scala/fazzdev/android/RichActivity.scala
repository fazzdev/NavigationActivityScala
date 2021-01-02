package fazzdev.android

import android.app.Activity
import android.os.AsyncTask
import android.view.View

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}

trait RichActivity extends Activity {
  implicit lazy val executionContext: ExecutionContextExecutor = ExecutionContext.fromExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

  implicit def toRunnable[T](action: => T): Runnable = new Runnable() {
    def run(): Unit = action
  }

  def findView[T <: View](id: Int): T = findViewById(id).asInstanceOf[T]
}
