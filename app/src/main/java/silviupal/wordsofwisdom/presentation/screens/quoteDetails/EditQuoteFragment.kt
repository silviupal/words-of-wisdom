package silviupal.wordsofwisdom.presentation.screens.quoteDetails

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_add_or_edit_quote.*
import kotlinx.android.synthetic.main.item_quote.*
import silviupal.wordsofwisdom.R
import silviupal.wordsofwisdom.common.ext.toSp
import silviupal.wordsofwisdom.domain.model.QuoteModel

/**
 * Created by Silviu Pal on 11/19/2019.
 */
class EditQuoteFragment : QuoteFragment() {
    companion object {
        @JvmField
        val TAG: String = EditQuoteFragment::class.java.simpleName
        private const val KEY_QUOTE_MODEL = "KEY_QUOTE_MODEL"

        fun newInstance(quote: QuoteModel) = EditQuoteFragment().apply {
            arguments = Bundle().apply {
                putSerializable(KEY_QUOTE_MODEL, quote)
            }
        }
    }

    override fun getToolbarTitle(): String = getString(R.string.fragment_edit_quote_title)
    override fun getConfirmationBtnText(): String = getString(R.string.btn_update_quote)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillViewsWithData(arguments?.getSerializable(KEY_QUOTE_MODEL) as? QuoteModel)
    }

    private fun fillViewsWithData(quoteModel: QuoteModel?) {
        quoteModel?.let { model ->
            this.model = model
            etQuoteAuthor.setText(model.author ?: "")
            etQuoteText.setText(model.quoteText)
            etQuoteText.setSelection(etQuoteText.text?.toString()?.length ?: 0)

            sbQuoteTextSize.progress = model.textSizeInPx
            tvQuoteText.textSize = model.textSizeInPx.toFloat().toSp

            // TODO Set image
        }
    }

    override fun updateDatabase(quoteModel: QuoteModel) {
        presenter.updateQuote(quoteModel)
    }

    override fun buildQuoteModel(): QuoteModel {
        val quoteText = etQuoteText.text!!.toString()
        val quoteAuthor = etQuoteAuthor.text?.toString()
        //TODO Add Image and Font
        return QuoteModel(this.model.id, quoteText, quoteAuthor, null, "", tvQuoteText.textSize.toInt())
    }
}