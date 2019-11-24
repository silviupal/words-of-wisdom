package silviupal.wordsofwisdom.presentation.screens.quotesList

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import silviupal.wordsofwisdom.R
import silviupal.wordsofwisdom.common.ext.hideOldFragment
import silviupal.wordsofwisdom.domain.model.QuoteModel
import silviupal.wordsofwisdom.presentation.screens.quoteDetails.AddQuoteFragment
import silviupal.wordsofwisdom.presentation.screens.quoteDetails.EditQuoteFragment

/**
 * Created by Silviu Pal on 11/19/2019.
 */
class QuotesListRouter(private val activity: FragmentActivity?) {
    fun routeToAddQuote(requestCode: Int, targetFragment: Fragment) {
        val fragment = AddQuoteFragment.newInstance()
        fragment.setTargetFragment(targetFragment, requestCode)
        activity?.supportFragmentManager?.beginTransaction()
                ?.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.fade_out, 0, android.R.anim.slide_out_right)
                ?.add(R.id.container, fragment, AddQuoteFragment.TAG)
                ?.addToBackStack(AddQuoteFragment.TAG)
                ?.hideOldFragment(activity)
                ?.commit()
    }

    fun routeToEditQuote(quote: QuoteModel, requestCode: Int, targetFragment: Fragment) {
        val fragment = EditQuoteFragment.newInstance(quote)
        fragment.setTargetFragment(targetFragment, requestCode)
        activity?.supportFragmentManager?.beginTransaction()
                ?.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.fade_out, 0, android.R.anim.slide_out_right)
                ?.add(R.id.container, fragment, EditQuoteFragment.TAG)
                ?.addToBackStack(EditQuoteFragment.TAG)
                ?.hideOldFragment(activity)
                ?.commit()
    }
}