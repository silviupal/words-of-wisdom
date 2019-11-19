package silviupal.wordsofwisdom.presentation.screens.editQuote

import android.os.Bundle
import silviupal.wordsofwisdom.R
import silviupal.wordsofwisdom.domain.model.QuoteModel
import silviupal.wordsofwisdom.presentation.base.BaseQuoteFragment

/**
 * Created by Silviu Pal on 11/19/2019.
 */
class EditQuoteFragment : BaseQuoteFragment() {
    companion object {
        private const val KEY_QUOTE_MODEL = "KEY_QUOTE_MODEL"

        @JvmField
        val TAG: String = EditQuoteFragment::class.java.simpleName

        fun newInstance(quote: QuoteModel) = EditQuoteFragment().apply {
            arguments = Bundle().apply {
                putSerializable(KEY_QUOTE_MODEL, quote)
            }
        }
    }

    override fun getToolbarTitle(): String = getString(R.string.fragment_edit_quote_title)
}