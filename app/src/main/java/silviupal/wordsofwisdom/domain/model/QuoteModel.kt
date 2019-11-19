package silviupal.wordsofwisdom.domain.model

import java.io.Serializable

/**
 * Created by Silviu Pal on 11/16/2019.
 */
data class QuoteModel(var id: Int,
                      val quoteText: String,
                      val author: String?,
                      val backgroundImageUrl: String?,
                      val font: String): Serializable