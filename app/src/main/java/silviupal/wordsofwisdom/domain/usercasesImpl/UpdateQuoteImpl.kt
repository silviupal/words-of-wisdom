package silviupal.wordsofwisdom.domain.usercasesImpl

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import silviupal.wordsofwisdom.domain.model.QuoteModel
import silviupal.wordsofwisdom.domain.model.toEntity
import silviupal.wordsofwisdom.domain.repositoryInterface.QuotesRepository
import silviupal.wordsofwisdom.domain.usecasesInterface.UpdateQuote

/**
 * Created by Silviu Pal on 11/24/2019.
 */
class UpdateQuoteImpl(private val quotesRepository: QuotesRepository) : UpdateQuote {
    override fun build(quoteModel: QuoteModel): Completable =
            quotesRepository.updateQuote(quoteModel.toEntity())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
}