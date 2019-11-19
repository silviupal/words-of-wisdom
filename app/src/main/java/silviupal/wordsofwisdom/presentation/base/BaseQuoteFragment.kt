package silviupal.wordsofwisdom.presentation.base

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.toolbar_layout.*
import silviupal.wordsofwisdom.R
import silviupal.wordsofwisdom.common.ext.initToolbar

/**
 * Created by Silviu Pal on 11/19/2019.
 */
abstract class BaseQuoteFragment : BaseFragment() {
    abstract fun getToolbarTitle(): String

    override fun getLayoutId(): Int = R.layout.fragment_add_or_edit_quote

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(toolbar)
        tbTitle.text = getToolbarTitle()

        /* TODO views to manipulate:
            android:id="@+id/ivBackImage"
            android:id="@+id/tvQuoteText"
            android:id="@+id/tvQuoteAuthor"
         */
    }
}