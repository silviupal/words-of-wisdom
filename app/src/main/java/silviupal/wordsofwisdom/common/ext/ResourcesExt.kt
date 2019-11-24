package silviupal.wordsofwisdom.common.ext

import android.content.res.Resources

/**
 * Created by Silviu Pal on 11/24/2019.
 */
val Float.toPx: Float get() = (this * Resources.getSystem().displayMetrics.density)

val Int.toPx: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Float.toSp: Float get() = this / Resources.getSystem().displayMetrics.scaledDensity

val Int.toSp: Int get() = this / Resources.getSystem().displayMetrics.scaledDensity.toInt()
