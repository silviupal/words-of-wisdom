package silviupal.wordsofwisdom.domain.model

/**
 * Created by Silviu Pal on 11/16/2019.
 */
data class QuoteModel(var id: Int,
                      val quote: String,
                      val author: String?,
                      val backgroundImageUrl: String?,
                      val font: String)