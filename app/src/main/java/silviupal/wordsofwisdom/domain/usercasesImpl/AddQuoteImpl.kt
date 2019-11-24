package silviupal.wordsofwisdom.domain.usercasesImpl

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import silviupal.wordsofwisdom.domain.model.QuoteModel
import silviupal.wordsofwisdom.domain.model.toEntity
import silviupal.wordsofwisdom.domain.repositoryInterface.QuotesRepository
import silviupal.wordsofwisdom.domain.usecasesInterface.AddQuote

/**
 * Created by Silviu Pal on 11/23/2019.
 */
class AddQuoteImpl(private val quotesRepository: QuotesRepository) : AddQuote {
    override fun build(quoteModel: QuoteModel): Completable =
            quotesRepository.addNewQuote(quoteModel.toEntity())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
}
