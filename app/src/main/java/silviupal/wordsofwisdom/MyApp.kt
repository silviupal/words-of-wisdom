package silviupal.wordsofwisdom

import android.app.Application
import silviupal.wordsofwisdom.data.MyDatabase
import silviupal.wordsofwisdom.data.RepositoryFactory
import silviupal.wordsofwisdom.presentation.UseCaseFactory

/**
 * Created by Silviu Pal on 11/1/2019.
 */
class MyApp : Application() {
    private lateinit var repositoryFactory: RepositoryFactory
    private lateinit var database: MyDatabase

    lateinit var useCaseFactory: UseCaseFactory

    override fun onCreate() {
        super.onCreate()
        database = MyDatabase.init(applicationContext)
        repositoryFactory = RepositoryFactory(database, applicationContext)
        useCaseFactory = UseCaseFactory.DefaultImpl(repositoryFactory)
    }
}