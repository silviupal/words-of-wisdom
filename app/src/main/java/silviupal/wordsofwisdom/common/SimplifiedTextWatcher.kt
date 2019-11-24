package silviupal.wordsofwisdom.common

import android.text.Editable
import android.text.TextWatcher

/**
 * Created by Silviu Pal on 11/23/2019.
 */
open class SimplifiedTextWatcher : TextWatcher {
    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}