package silviupal.wordsofwisdom.domain.repositoryInterface

import io.reactivex.Completable
import io.reactivex.Single
import silviupal.wordsofwisdom.data.entity.QuoteEntity
import silviupal.wordsofwisdom.domain.model.QuoteModel

/**
 * Created by Silviu Pal on 11/10/2019.
 */
interface QuotesRepository {
    fun getQuotesList(): Single<ArrayList<QuoteModel>>
    fun addNewQuote(quote: QuoteEntity): Completable
    fun deleteQuote(quote: QuoteEntity): Completable
    fun updateQuote(quote: QuoteEntity): Completable
    fun getQuoteById(id: Int): Single<QuoteModel>
}