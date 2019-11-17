package silviupal.wordsofwisdom.presentation.screens.quotesList

import silviupal.wordsofwisdom.presentation.UseCaseFactory
import silviupal.wordsofwisdom.presentation.base.BasePresenter

/**
 * Created by Silviu Pal on 11/10/2019.
 */
class QuotesListPresenter(private val useCaseFactory: UseCaseFactory) : BasePresenter<QuotesListView>() {
    override var view: QuotesListView? = null

    override fun attach(view: QuotesListView) {
        this.view = view
    }

    fun getQuotesList() {
        useCaseFactory.getQuotesListUseCase().build()
                .subscribe({
                    // build quotes list
                }, { error ->
                    // show error
                })
    }
}