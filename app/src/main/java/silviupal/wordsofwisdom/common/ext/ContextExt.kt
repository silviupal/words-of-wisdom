package silviupal.wordsofwisdom.common.ext

import android.content.Context
import silviupal.wordsofwisdom.MyApp

/**
 * Created by Silviu Pal on 11/17/2019.
 */
val Context.app: MyApp get() = applicationContext as MyApp
