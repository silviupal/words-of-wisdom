package silviupal.wordsofwisdom.common.ext

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import silviupal.wordsofwisdom.MyApp
import silviupal.wordsofwisdom.WowWidget


/**
 * Created by Silviu Pal on 11/17/2019.
 */
val Context.app: MyApp get() = applicationContext as MyApp

fun Context.hideKeyboard(view: View) {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.updateWidget() {
    sendBroadcast(Intent(this, WowWidget::class.java).apply {
        action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
    })
}