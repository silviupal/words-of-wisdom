package silviupal.wordsofwisdom.data.dao

import androidx.room.*
import silviupal.wordsofwisdom.data.entity.QuoteEntity

/**
 * Created by Silviu Pal on 11/16/2019.
 */
@Dao
interface DaoQuoteAccess {
    @Insert
    fun insertOneItem(quote: QuoteEntity)
    
    @Update
    fun updateOneItem(post: QuoteEntity)

    @Delete
    fun deleteOneItem(post: QuoteEntity)

    @Query("SELECT * FROM quotes WHERE id = :itemId")
    fun getListItemById(itemId: Int): QuoteEntity?

    @Query("SELECT * FROM quotes")
    fun getAllItems(): List<QuoteEntity>
}