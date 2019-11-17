package silviupal.wordsofwisdom.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import silviupal.wordsofwisdom.domain.model.QuoteModel

/**
 * Created by Silviu Pal on 11/10/2019.
 */
@Entity(tableName = "quotes")
data class QuoteEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        val quote: String,
        val author: String?,
        val backgroundImageUrl: String?,
        val font: String)

fun QuoteEntity.toQuoteModel(): QuoteModel =
            QuoteModel(id, quote, author, backgroundImageUrl, font)