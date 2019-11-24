package silviupal.wordsofwisdom.domain.usecasesInterface

import io.reactivex.Completable
import silviupal.wordsofwisdom.domain.model.QuoteModel

/**
 * Created by Silviu Pal on 11/24/2019.
 */
interface UpdateQuote {
    fun build(quoteModel: QuoteModel): Completable
}