package silviupal.wordsofwisdom.presentation.screens.quoteDetails

import silviupal.wordsofwisdom.domain.model.QuoteModel
import silviupal.wordsofwisdom.presentation.UseCaseFactory
import silviupal.wordsofwisdom.presentation.base.BasePresenter

/**
 * Created by Silviu Pal on 11/23/2019.
 */
class QuotePresenter(private val useCaseFactory: UseCaseFactory) : BasePresenter<QuoteView>() {
    override var view: QuoteView? = null

    fun addQuote(quoteModel: QuoteModel) {
        disposable.add(useCaseFactory.addQuoteUseCase().build(quoteModel)
                .subscribe({
                    view?.updateView()
                }, {
                    view?.showError()
                }))
    }

    fun updateQuote(quoteModel: QuoteModel) {
        disposable.add(useCaseFactory.updateQuoteUseCase().build(quoteModel)
                .subscribe({
                    view?.updateView()
                }, {
                    view?.showError()
                })
        )
    }
}