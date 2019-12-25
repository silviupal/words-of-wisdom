package silviupal.wordsofwisdom

import android.app.Application
import android.content.SharedPreferences
import silviupal.wordsofwisdom.data.MyDatabase
import silviupal.wordsofwisdom.data.RepositoryFactory
import silviupal.wordsofwisdom.presentation.UseCaseFactory

/**
 * Created by Silviu Pal on 11/1/2019.
 */
class MyApp : Application() {
    private lateinit var repositoryFactory: RepositoryFactory
    private lateinit var database: MyDatabase

    lateinit var sharedPrefs: SharedPreferences
    lateinit var useCaseFactory: UseCaseFactory

    companion object {
        private const val SHARED_PREFS_FILE = "wow_widget"
    }

    override fun onCreate() {
        super.onCreate()
        database = MyDatabase.init(applicationContext)
        repositoryFactory = RepositoryFactory(database, applicationContext)
        useCaseFactory = UseCaseFactory.DefaultImpl(repositoryFactory)
        sharedPrefs = getSharedPreferences(SHARED_PREFS_FILE, MODE_PRIVATE)
    }
}