package silviupal.wordsofwisdom.presentation.screens.quotesList

import silviupal.wordsofwisdom.domain.model.QuoteModel

/**
 * Created by Silviu Pal on 11/10/2019.
 */
interface QuotesListView {
    fun populateQuotesList(quotesList: ArrayList<QuoteModel>)
    fun showProgress()
    fun hideProgress()
    fun showError()
    fun showEmptyScreen()
}