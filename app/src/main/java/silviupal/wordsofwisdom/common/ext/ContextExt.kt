package silviupal.wordsofwisdom.common.ext

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import silviupal.wordsofwisdom.MyApp


/**
 * Created by Silviu Pal on 11/17/2019.
 */
val Context.app: MyApp get() = applicationContext as MyApp

fun Context.hideKeyboard(view: View) {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}