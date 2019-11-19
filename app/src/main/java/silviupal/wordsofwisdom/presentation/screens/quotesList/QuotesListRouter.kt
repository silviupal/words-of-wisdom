package silviupal.wordsofwisdom.presentation.screens.quotesList

import androidx.fragment.app.FragmentActivity
import silviupal.wordsofwisdom.R
import silviupal.wordsofwisdom.domain.model.QuoteModel
import silviupal.wordsofwisdom.presentation.screens.addQuote.AddQuoteFragment
import silviupal.wordsofwisdom.presentation.screens.editQuote.EditQuoteFragment

/**
 * Created by Silviu Pal on 11/19/2019.
 */
class QuotesListRouter(private val activity: FragmentActivity?) {
    fun routeToAddQuote() {
        activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, AddQuoteFragment.newInstance(), AddQuoteFragment.TAG)
                ?.addToBackStack(AddQuoteFragment.TAG)
                ?.commit()
    }

    fun routeToEditQuote(quote: QuoteModel) {
        activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, EditQuoteFragment.newInstance(quote), EditQuoteFragment.TAG)
                ?.addToBackStack(EditQuoteFragment.TAG)
                ?.commit()
    }
}