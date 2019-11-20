package silviupal.wordsofwisdom.data.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import silviupal.wordsofwisdom.data.entity.QuoteEntity

/**
 * Created by Silviu Pal on 11/16/2019.
 */
@Dao
interface DaoQuoteAccess {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOneItem(quote: QuoteEntity): Completable

    @Update
    fun updateOneItem(post: QuoteEntity): Completable

    @Delete
    fun deleteOneItem(post: QuoteEntity): Completable

    @Query("SELECT * FROM quotes WHERE id = :itemId")
    fun getListItemById(itemId: Int): Single<QuoteEntity?>

    @Query("SELECT * FROM quotes")
    fun getAllItems(): Single<List<QuoteEntity>>
}