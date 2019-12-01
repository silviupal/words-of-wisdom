package silviupal.wordsofwisdom.common

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import silviupal.wordsofwisdom.R


/**
 * Created by Silviu Pal on 11/25/2019.
 */
class FontsFactory(private val context: Context) {
    enum class Fonts(val fontName: String) {
        DEFAULT("default"),
        FONT2("beautifulpeople"),
        FONT3("beautymountains"),
        FONT4("fallcoming"),
        FONT5("quickkiss"),
        FONT6("quitemagical")
    }

    fun getTypeface(font: Fonts): Typeface {
        return when (font) {
            Fonts.DEFAULT -> {
                Typeface.create("sans-serif-light", Typeface.NORMAL)
            }
            Fonts.FONT2 -> {
                ResourcesCompat.getFont(context, R.font.beautifulpeople)!!
            }
            Fonts.FONT3 -> {
                ResourcesCompat.getFont(context, R.font.beautymountains)!!
            }
            Fonts.FONT4 -> {
                ResourcesCompat.getFont(context, R.font.fallcoming)!!
            }
            Fonts.FONT5 -> {
                ResourcesCompat.getFont(context, R.font.quickkiss)!!
            }
            Fonts.FONT6 -> {
                ResourcesCompat.getFont(context, R.font.quitemagical)!!
            }
        }
    }
}