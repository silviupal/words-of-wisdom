package silviupal.wordsofwisdom.presentation.screens.quotesList

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.empty_screen_layout.*
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
    private lateinit var router: QuotesListRouter

    private val presenter by lazy {
        QuotesListPresenter(context!!.app.useCaseFactory)
    }

    companion object {
        @JvmField
        val TAG: String = QuotesListFragment::class.java.simpleName
        private const val REQUEST_CODE_ADD = 10
        private const val REQUEST_CODE_EDIT = 11

        fun newInstance() = QuotesListFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_quotes_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = QuotesListRouter(activity)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(toolbar)
        tbTitle.text = getString(R.string.fragment_quotes_list_title)

        initRecyclerView()
        setAddNewQuoteAction()
        presenter.attach(this)
        presenter.getQuotesList()
    }

    private fun initRecyclerView() {
        context?.let { ctx ->
            val adapter = QuotesListAdapter(ctx, ::showEditQuotePage)
            rvQuotesList.adapter = adapter
            rvQuotesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        fab.hide()
                    } else {
                        fab.show()
                    }
                }
            })
        }
    }

    private fun setAddNewQuoteAction() {
        val addQuoteListener = View.OnClickListener { router.routeToAddQuote(REQUEST_CODE_ADD, this) }
        fab.setOnClickListener(addQuoteListener)
        ivAddQuote.setOnClickListener(addQuoteListener)
    }

    private fun showEditQuotePage(quote: QuoteModel) {
        router.routeToEditQuote(quote, REQUEST_CODE_EDIT, this)
    }

    @SuppressLint("RestrictedApi")
    override fun showEmptyScreen() {
        emptyLayout.visibility = View.VISIBLE
        rvQuotesList.visibility = View.GONE
        fab.visibility = View.GONE
    }

    @SuppressLint("RestrictedApi")
    override fun populateQuotesList(quotesList: ArrayList<QuoteModel>) {
        emptyLayout.visibility = View.GONE
        rvQuotesList.visibility = View.VISIBLE
        fab.visibility = View.VISIBLE

        (rvQuotesList?.adapter as? QuotesListAdapter)?.setList(quotesList)
        if (quotesList.isNotEmpty()) rvQuotesList?.scrollToPosition(0)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_ADD -> {
                    presenter.attach(this)
                    presenter.getQuotesList()
                }
                REQUEST_CODE_EDIT -> {
                    presenter.attach(this)
                    presenter.getQuotesList()
                }
            }
        }
    }

    override fun onDestroyView() {
        presenter.detach()
        super.onDestroyView()
    }
}