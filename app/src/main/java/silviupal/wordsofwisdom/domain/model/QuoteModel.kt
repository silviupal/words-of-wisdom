package silviupal.wordsofwisdom.domain.model

import silviupal.wordsofwisdom.data.entity.QuoteEntity
import java.io.Serializable

/**
 * Created by Silviu Pal on 11/16/2019.
 */
data class QuoteModel(var id: Int = 0,
                      val quoteText: String,
                      val author: String?,
                      val image: ByteArray?,
                      val font: String,
                      val textSizeInPx: Int) : Serializable {

    constructor(quoteText: String, author: String?, image: ByteArray?, font: String, textSizeInPx: Int) :
            this(0, quoteText, author, image, font, textSizeInPx)
}

fun QuoteModel.toEntity(): QuoteEntity =
        QuoteEntity(id, quoteText, author, image, font, textSizeInPx)