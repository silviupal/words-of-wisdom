package silviupal.wordsofwisdom.domain.usecasesInterface

import io.reactivex.Completable
import silviupal.wordsofwisdom.domain.model.QuoteModel

/**
 * Created by Silviu Pal on 11/23/2019.
 */
interface AddQuote {
    fun build(quoteModel: QuoteModel): Completable
}