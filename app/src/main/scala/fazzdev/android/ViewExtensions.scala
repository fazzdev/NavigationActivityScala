package fazzdev.android

import android.animation.{Animator, AnimatorListenerAdapter}
import android.annotation.TargetApi
import android.os.{Build, Looper}
import android.view.View.OnClickListener
import android.view.{KeyEvent, View, ViewPropertyAnimator}
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import com.google.android.material.snackbar.Snackbar

object ViewExtensions {

  implicit class RichView(val view: View) extends AnyVal {
    def onClick(action: View => Any) {
      view.setOnClickListener(new View.OnClickListener {
        override def onClick(onClickView: View): Unit = action(onClickView)
      })
    }

    def setVisibility(isVisible: Boolean): Unit = {
      if (isVisible)
        view.setVisibility(View.VISIBLE)
      else
        view.setVisibility(View.GONE)
    }

    def runOnUiThread(action: Runnable): Unit = {
      val uiThread = Looper.getMainLooper.getThread
      if (Thread.currentThread != uiThread)
        view.post(action)
      else
        action.run()
    }
  }

  implicit class RichTextView(val view: TextView) extends AnyVal {
    def onEditorAction(action: (TextView, Int, KeyEvent) => Boolean) {
      view.setOnEditorActionListener(new OnEditorActionListener {
        override def onEditorAction(textView: TextView, id: Int, keyEvent: KeyEvent): Boolean = action(textView, id, keyEvent)
      })
    }
  }

  implicit class RichSnackbar(val snackbar: Snackbar) extends AnyVal {
    def onOkClick(action: View => Any) {
      snackbar.setAction(android.R.string.ok, new OnClickListener {
        @TargetApi(Build.VERSION_CODES.M)
        override def onClick(view: View): Unit = action(view)
      })
    }
  }

  implicit class RichViewPropertyAnimator(val viewPropertyAnimator: ViewPropertyAnimator) extends AnyVal {
    def onAnimationEnd(action: Animator => Any) {
      viewPropertyAnimator.setListener(new AnimatorListenerAdapter {
        override def onAnimationEnd(animator: Animator): Unit = action(animator)
      })
    }
  }

}
