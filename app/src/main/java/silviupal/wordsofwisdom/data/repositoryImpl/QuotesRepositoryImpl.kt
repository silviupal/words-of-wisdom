package silviupal.wordsofwisdom.data.repositoryImpl

import io.reactivex.Completable
import io.reactivex.Single
import silviupal.wordsofwisdom.data.MyDatabase
import silviupal.wordsofwisdom.data.entity.QuoteEntity
import silviupal.wordsofwisdom.data.entity.toQuoteModel
import silviupal.wordsofwisdom.domain.model.QuoteModel
import silviupal.wordsofwisdom.domain.repositoryInterface.QuotesRepository

/**
 * Created by Silviu Pal on 11/10/2019.
 */
class QuotesRepositoryImpl(private val database: MyDatabase) : QuotesRepository {
    override fun addNewQuote(quote: QuoteEntity): Completable =
            database.daoQuote().insertOneItem(quote)

    override fun deleteQuote(quote: QuoteEntity): Completable =
            database.daoQuote().deleteOneItem(quote)

    override fun updateQuote(quote: QuoteEntity): Completable =
            database.daoQuote().updateOneItem(quote)

    override fun getQuoteById(id: Int): Single<QuoteModel> =
            database.daoQuote().getListItemById(id)
                    .map(QuoteEntity::toQuoteModel)

    override fun getQuotesList(): Single<ArrayList<QuoteModel>> =
            database.daoQuote().getAllItems()
                    .map { quotesList ->
                        val list = arrayListOf<QuoteModel>()
                        quotesList.forEach { list += it.toQuoteModel() }
                        list
                    }
}