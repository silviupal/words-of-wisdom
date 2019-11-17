package silviupal.wordsofwisdom.presentation.activities

import android.os.Bundle
import silviupal.wordsofwisdom.R
import silviupal.wordsofwisdom.presentation.base.BaseActivity
import silviupal.wordsofwisdom.presentation.screens.quotesList.QuotesListFragment

/**
 * Created by Silviu Pal on 11/1/2019.
 */
class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragment(QuotesListFragment.newInstance(), QuotesListFragment.TAG)
    }
}