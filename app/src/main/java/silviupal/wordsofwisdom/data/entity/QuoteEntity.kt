package silviupal.wordsofwisdom.data.entity

import androidx.room.ColumnInfo
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
        @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
        val image: ByteArray?,
        val font: String,
        val textSizeInPx: Int)

fun QuoteEntity.toQuoteModel(): QuoteModel =
        QuoteModel(id, quote, author, image, font, textSizeInPx)