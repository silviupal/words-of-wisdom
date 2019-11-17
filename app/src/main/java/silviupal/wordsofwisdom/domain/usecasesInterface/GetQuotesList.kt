package silviupal.wordsofwisdom.domain.usecasesInterface

import io.reactivex.Single
import silviupal.wordsofwisdom.domain.model.QuoteModel

/**
 * Created by Silviu Pal on 11/10/2019.
 */
interface GetQuotesList {
    fun build(): Single<ArrayList<QuoteModel>>
}