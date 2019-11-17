package silviupal.wordsofwisdom.data

import android.content.Context
import silviupal.wordsofwisdom.data.repositoryImpl.QuotesRepositoryImpl

/**
 * Created by Silviu Pal on 11/10/2019.
 */
class RepositoryFactory(private val database: MyDatabase, applicationContext: Context) {
    val quotesRepository by lazy {
        QuotesRepositoryImpl(database)
    }
}