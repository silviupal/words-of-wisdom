package silviupal.wordsofwisdom.presentation.screens.quoteDetails

import kotlinx.android.synthetic.main.fragment_add_or_edit_quote.*
import kotlinx.android.synthetic.main.item_quote.*
import silviupal.wordsofwisdom.R
import silviupal.wordsofwisdom.domain.model.QuoteModel

/**
 * Created by Silviu Pal on 11/19/2019.
 */
class AddQuoteFragment : QuoteFragment() {
    companion object {
        @JvmField
        val TAG: String = AddQuoteFragment::class.java.simpleName

        fun newInstance() = AddQuoteFragment()
    }

    override fun getToolbarTitle(): String = getString(R.string.fragment_add_quote_title)

    override fun getConfirmationBtnText(): String = getString(R.string.btn_save_quote)

    override fun updateDatabase(quoteModel: QuoteModel) {
        presenter.addQuote(quoteModel)
    }

    override fun buildQuoteModel(): QuoteModel {
        val quoteText = etQuoteText.text!!.toString()
        val quoteAuthor = etQuoteAuthor.text?.toString()
        //TODO Add Image and Font
        return QuoteModel(quoteText, quoteAuthor, null, "", tvQuoteText.textSize.toInt())
    }
}