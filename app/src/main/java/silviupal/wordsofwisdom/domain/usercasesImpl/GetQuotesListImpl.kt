package silviupal.wordsofwisdom.domain.usercasesImpl

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import silviupal.wordsofwisdom.domain.model.QuoteModel
import silviupal.wordsofwisdom.domain.repositoryInterface.QuotesRepository
import silviupal.wordsofwisdom.domain.usecasesInterface.GetQuotesList

/**
 * Created by Silviu Pal on 11/10/2019.
 */
class GetQuotesListImpl(private val quotesRepository: QuotesRepository) : GetQuotesList {
    override fun build(): Single<ArrayList<QuoteModel>> =
            quotesRepository.getQuotesList()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
}