package silviupal.wordsofwisdom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import silviupal.wordsofwisdom.data.dao.DaoQuoteAccess
import silviupal.wordsofwisdom.data.entity.QuoteEntity

/**
 * Created by Silviu Pal on 11/10/2019.
 */
@Database(entities = [QuoteEntity::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun daoQuote(): DaoQuoteAccess

    companion object {
        private const val DATABASE_NAME: String = "quotes_db"
        fun init(applicationContext: Context): MyDatabase {
            return Room.databaseBuilder(
                    applicationContext,
                    MyDatabase::class.java,
                    DATABASE_NAME
            ).build()
        }
    }
}