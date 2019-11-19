package silviupal.wordsofwisdom.presentation.screens.addQuote

import silviupal.wordsofwisdom.R
import silviupal.wordsofwisdom.presentation.base.BaseQuoteFragment

/**
 * Created by Silviu Pal on 11/19/2019.
 */
class AddQuoteFragment : BaseQuoteFragment() {
    companion object {
        @JvmField
        val TAG: String = AddQuoteFragment::class.java.simpleName

        fun newInstance() = AddQuoteFragment()
    }

    override fun getToolbarTitle(): String = getString(R.string.fragment_add_quote_title)
}