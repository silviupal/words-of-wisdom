package silviupal.wordsofwisdom.presentation.screens.quotesList

import android.os.Bundle
import android.view.View
import silviupal.wordsofwisdom.R
import silviupal.wordsofwisdom.common.ext.app
import silviupal.wordsofwisdom.presentation.base.BaseFragment

/**
 * Created by Silviu Pal on 11/1/2019.
 */
class QuotesListFragment : BaseFragment(), QuotesListView {
    lateinit var presenter: QuotesListPresenter

    companion object {
        fun newInstance() = QuotesListFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_quotes_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = QuotesListPresenter(context!!.app.useCaseFactory)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.getQuotesList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }
}