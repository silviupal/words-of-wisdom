package silviupal.wordsofwisdom.presentation

import silviupal.wordsofwisdom.data.RepositoryFactory
import silviupal.wordsofwisdom.domain.usecasesInterface.AddQuote
import silviupal.wordsofwisdom.domain.usecasesInterface.UpdateQuote
import silviupal.wordsofwisdom.domain.usecasesInterface.GetQuotesList
import silviupal.wordsofwisdom.domain.usercasesImpl.AddQuoteImpl
import silviupal.wordsofwisdom.domain.usercasesImpl.UpdateQuoteImpl
import silviupal.wordsofwisdom.domain.usercasesImpl.GetQuotesListImpl

/**
 * Created by Silviu Pal on 11/10/2019.
 * Returns instances of all the usecases in the app
 */
interface UseCaseFactory {
    fun getQuotesListUseCase(): GetQuotesList
    fun addQuoteUseCase(): AddQuote
    fun updateQuoteUseCase(): UpdateQuote

    class DefaultImpl(private val repositoryFactory: RepositoryFactory) : UseCaseFactory {
        override fun getQuotesListUseCase(): GetQuotesList =
                GetQuotesListImpl(repositoryFactory.quotesRepository)

        override fun addQuoteUseCase(): AddQuote =
                AddQuoteImpl(repositoryFactory.quotesRepository)

        override fun updateQuoteUseCase(): UpdateQuote =
                UpdateQuoteImpl(repositoryFactory.quotesRepository)
    }
}