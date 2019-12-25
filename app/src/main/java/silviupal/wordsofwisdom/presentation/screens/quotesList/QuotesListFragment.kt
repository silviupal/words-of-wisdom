package silviupal.wordsofwisdom.presentation.screens.quotesList

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.empty_screen_layout.*
import kotlinx.android.synthetic.main.fragment_quotes_list.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import silviupal.wordsofwisdom.R
import silviupal.wordsofwisdom.WowWidget
import silviupal.wordsofwisdom.common.ext.app
import silviupal.wordsofwisdom.common.ext.initToolbar
import silviupal.wordsofwisdom.common.ext.updateWidget
import silviupal.wordsofwisdom.domain.model.QuoteModel
import silviupal.wordsofwisdom.presentation.base.BaseFragment

/**
 * Created by Silviu Pal on 11/1/2019.
 */
class QuotesListFragment : BaseFragment(), QuoteListView {
    private lateinit var router: QuotesListRouter

    private val presenter by lazy {
        QuoteListPresenter(context!!.app.useCaseFactory)
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
        presenter.getQuoteList()
    }

    private fun initRecyclerView() {
        context?.let { ctx ->
            val adapter = QuoteListAdapter(ctx, ::showEditQuotePage)
            rvQuoteList.adapter = adapter
            rvQuoteList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        fabAddQuote.hide()
                    } else {
                        fabAddQuote.show()
                    }
                }
            })
        }
    }

    private fun setAddNewQuoteAction() {
        val addQuoteListener = View.OnClickListener { router.routeToAddQuote(REQUEST_CODE_ADD, this) }
        fabAddQuote.setOnClickListener(addQuoteListener)
        ivAddQuote.setOnClickListener(addQuoteListener)
    }

    private fun showEditQuotePage(quote: QuoteModel) {
        router.routeToEditQuote(quote, REQUEST_CODE_EDIT, this)
    }

    @SuppressLint("RestrictedApi")
    override fun showEmptyScreen() {
        context?.let {
            it.updateWidget()
            it.app.sharedPrefs.edit()
                    .putInt(WowWidget.PREFS_POSITION, -1)
                    .apply()
        }

        emptyLayout.visibility = View.VISIBLE
        rvQuoteList.visibility = View.GONE
        fabAddQuote.visibility = View.GONE
    }

    @SuppressLint("RestrictedApi")
    override fun populateQuoteList(quoteList: ArrayList<QuoteModel>) {
        context?.let(Context::updateWidget)

        emptyLayout.visibility = View.GONE
        rvQuoteList.visibility = View.VISIBLE
        fabAddQuote.visibility = View.VISIBLE
        (rvQuoteList?.adapter as? QuoteListAdapter)?.setList(quoteList)
        if (quoteList.isNotEmpty()) rvQuoteList?.scrollToPosition(0)
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
                REQUEST_CODE_ADD,
                REQUEST_CODE_EDIT -> {
                    presenter.attach(this)
                    presenter.getQuoteList()
                }
            }
        }
    }

    override fun onDestroyView() {
        presenter.detach()
        super.onDestroyView()
    }
}