package silviupal.wordsofwisdom.presentation.screens.quotesList

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_quotes_list.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import silviupal.wordsofwisdom.R
import silviupal.wordsofwisdom.common.ext.app
import silviupal.wordsofwisdom.common.ext.initToolbar
import silviupal.wordsofwisdom.domain.model.QuoteModel
import silviupal.wordsofwisdom.presentation.base.BaseFragment

/**
 * Created by Silviu Pal on 11/1/2019.
 */
class QuotesListFragment : BaseFragment(), QuotesListView {
    lateinit var presenter: QuotesListPresenter

    companion object {
        @JvmField
        val TAG: String = QuotesListFragment::class.java.simpleName

        fun newInstance() = QuotesListFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_quotes_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = QuotesListPresenter(context!!.app.useCaseFactory)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(toolbar)
        tbTitle.text = getString(R.string.fragment_quotes_list_title)

        initRecyclerView()
        presenter.attach(this)
        presenter.getQuotesList()
    }

    private fun initRecyclerView() {
        context?.let { ctx ->
            val adapter = QuotesListAdapter(ctx)
            rvQuotesList.adapter = adapter
        }
    }

    override fun populateQuotesList(quotesList: ArrayList<QuoteModel>) {
        (rvQuotesList?.adapter as? QuotesListAdapter)?.setList(quotesList)
    }

    override fun showProgress() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar?.visibility = View.GONE
    }

    override fun showError() {
        rootLayout?.let {
            Snackbar.make(it, "Data not accessible", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }
}