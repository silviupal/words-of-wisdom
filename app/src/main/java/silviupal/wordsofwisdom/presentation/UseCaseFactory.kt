package silviupal.wordsofwisdom.presentation

import silviupal.wordsofwisdom.data.RepositoryFactory
import silviupal.wordsofwisdom.domain.usecasesInterface.GetQuotesList
import silviupal.wordsofwisdom.domain.usercasesImpl.GetQuotesListImpl

/**
 * Created by Silviu Pal on 11/10/2019.
 * Returns instances of all the usecases in the app
 */
interface UseCaseFactory {
    fun getQuotesListUseCase(): GetQuotesList

    class DefaultImpl(private val repositoryFactory: RepositoryFactory) : UseCaseFactory {
        override fun getQuotesListUseCase(): GetQuotesList =
            GetQuotesListImpl(repositoryFactory.quotesRepository)
    }
}