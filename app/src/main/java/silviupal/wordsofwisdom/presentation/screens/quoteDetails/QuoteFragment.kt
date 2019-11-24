package silviupal.wordsofwisdom.presentation.screens.quoteDetails

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.SeekBar
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_or_edit_quote.*
import kotlinx.android.synthetic.main.item_quote.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import silviupal.wordsofwisdom.R
import silviupal.wordsofwisdom.common.SimplifiedSeekBarListener
import silviupal.wordsofwisdom.common.SimplifiedTextWatcher
import silviupal.wordsofwisdom.common.ext.app
import silviupal.wordsofwisdom.common.ext.hideKeyboard
import silviupal.wordsofwisdom.common.ext.initToolbar
import silviupal.wordsofwisdom.common.ext.toSp
import silviupal.wordsofwisdom.domain.model.QuoteModel
import silviupal.wordsofwisdom.presentation.base.BaseFragment

/**
 * Created by Silviu Pal on 11/19/2019.
 */
abstract class QuoteFragment : BaseFragment(), QuoteView {
    private lateinit var quoteWatcher: TextWatcher
    private lateinit var authorWatcher: TextWatcher
    lateinit var model: QuoteModel

    abstract fun getToolbarTitle(): String
    abstract fun getConfirmationBtnText(): String
    abstract fun updateDatabase(quoteModel: QuoteModel)
    abstract fun buildQuoteModel(): QuoteModel

    protected val presenter by lazy {
        QuotePresenter(context!!.app.useCaseFactory)
    }

    override fun getLayoutId(): Int = R.layout.fragment_add_or_edit_quote

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(toolbar)
        presenter.attach(this)
        tbTitle.text = getToolbarTitle()
        initScreen()
    }

    private fun initScreen() {
        btnConfirm.text = getConfirmationBtnText()
        setWatchers()
        setEditActions()
        setTextSizeSlider()
    }

    private fun setEditActions() {
        ivEditImage.visibility = View.VISIBLE

        ivEditImage.setOnClickListener {
            // TODO
        }

        btnConfirm.setOnClickListener {
            if (isFormValidated()) {
                updateDatabase(buildQuoteModel())
            }
        }
    }

    private fun setWatchers() {
        quoteWatcher = object : SimplifiedTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                super.afterTextChanged(s)
                setQuoteTextError(null)
                tvQuoteText.text = s?.toString() ?: ""
            }
        }

        authorWatcher = object : SimplifiedTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                super.afterTextChanged(s)
                tvQuoteAuthor.text = s?.toString() ?: ""
            }
        }

        etQuoteText.addTextChangedListener(quoteWatcher)
        etQuoteAuthor.addTextChangedListener(authorWatcher)
        etQuoteText.setOnEditorActionListener(getEditorListener())
        etQuoteAuthor.setOnEditorActionListener(getEditorListener())
    }

    private fun getEditorListener(): TextView.OnEditorActionListener {
        return TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                context?.hideKeyboard(etQuoteAuthor)
                true
            } else false
        }
    }

    private fun setTextSizeSlider() {
        sbQuoteTextSize.apply {
            progress = tvQuoteText.textSize.toInt()
            tvTextSizeCounter.text = progress.toString()

            setOnSeekBarChangeListener(object : SimplifiedSeekBarListener() {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    super.onProgressChanged(seekBar, progress, fromUser)
                    seekBar?.let { sb ->
                        tvTextSizeCounter.text = progress.toString()
                        tvQuoteText.textSize = progress.toFloat().toSp
                    }
                }
            })
        }

        //TODO - use setTextSize(TypedValue.COMPLEX_UNIT_PX, size) size in pixels
        // getTextSizeInPx() returns pixels

        // TODO Establish a UNIT to measure text size. Pixels or SP. Convert correctly
    }


    private fun isFormValidated(): Boolean =
            if (etQuoteText.text!!.isEmpty()) {
                setQuoteTextError(getString(R.string.error_empty_field))
                false
            } else {
                true
            }

    private fun setQuoteTextError(error: String?) {
        tilQuoteText.error = error
    }

    override fun updateView() {
        if (targetFragment != null) {
            targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_OK, null)
        }
        activity?.supportFragmentManager?.popBackStack()
    }

    override fun showError() {
        rootLayout?.let {
            Snackbar.make(it, "Can't update database", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        rootLayout?.let { context?.hideKeyboard(it) }
        presenter.detach()
        super.onDestroyView()
    }
}
