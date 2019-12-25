package silviupal.wordsofwisdom.domain.usercasesImpl

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import silviupal.wordsofwisdom.domain.model.QuoteModel
import silviupal.wordsofwisdom.domain.repositoryInterface.QuotesRepository
import silviupal.wordsofwisdom.domain.usecasesInterface.GetQuoteList

/**
 * Created by Silviu Pal on 11/10/2019.
 */
class GetQuoteListImpl(private val quotesRepository: QuotesRepository) : GetQuoteList {
    override fun build(): Single<ArrayList<QuoteModel>> =
            quotesRepository.getQuotesList()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
}