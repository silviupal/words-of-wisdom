package silviupal.wordsofwisdom.presentation.screens.quotesList

import silviupal.wordsofwisdom.presentation.UseCaseFactory
import silviupal.wordsofwisdom.presentation.base.BasePresenter

/**
 * Created by Silviu Pal on 11/10/2019.
 */
class QuoteListPresenter(private val useCaseFactory: UseCaseFactory) : BasePresenter<QuoteListView>() {
    override var view: QuoteListView? = null

    fun getQuoteList() {
        view?.showProgress()
        disposable.add(useCaseFactory.getQuoteListUseCase().build()
                .subscribe({
                    view?.hideProgress()
                    if (it.size == 0) {
                        view?.showEmptyScreen()
                    } else {
                        view?.populateQuoteList(it)
                    }
                }, {
                    view?.hideProgress()
                    view?.showError()
                }))
    }
}