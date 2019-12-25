package silviupal.wordsofwisdom.presentation

import silviupal.wordsofwisdom.data.RepositoryFactory
import silviupal.wordsofwisdom.domain.usecasesInterface.AddQuote
import silviupal.wordsofwisdom.domain.usecasesInterface.UpdateQuote
import silviupal.wordsofwisdom.domain.usecasesInterface.GetQuoteList
import silviupal.wordsofwisdom.domain.usercasesImpl.AddQuoteImpl
import silviupal.wordsofwisdom.domain.usercasesImpl.UpdateQuoteImpl
import silviupal.wordsofwisdom.domain.usercasesImpl.GetQuoteListImpl

/**
 * Created by Silviu Pal on 11/10/2019.
 * Returns instances of all the usecases in the app
 */
interface UseCaseFactory {
    fun getQuoteListUseCase(): GetQuoteList
    fun addQuoteUseCase(): AddQuote
    fun updateQuoteUseCase(): UpdateQuote

    class DefaultImpl(private val repositoryFactory: RepositoryFactory) : UseCaseFactory {
        override fun getQuoteListUseCase(): GetQuoteList =
                GetQuoteListImpl(repositoryFactory.quotesRepository)

        override fun addQuoteUseCase(): AddQuote =
                AddQuoteImpl(repositoryFactory.quotesRepository)

        override fun updateQuoteUseCase(): UpdateQuote =
                UpdateQuoteImpl(repositoryFactory.quotesRepository)
    }
}