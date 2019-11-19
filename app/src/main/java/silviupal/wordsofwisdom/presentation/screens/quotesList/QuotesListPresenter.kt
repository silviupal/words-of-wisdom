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
        view?.showProgress()
        useCaseFactory.getQuotesListUseCase().build()
                .subscribe({
                    view?.hideProgress()
                    if (it.size == 0) {
                        view?.showEmptyScreen()
                    } else {
                        view?.populateQuotesList(it)
                    }
                }, { error ->
                    view?.hideProgress()
                    view?.showError()
                })
    }
}