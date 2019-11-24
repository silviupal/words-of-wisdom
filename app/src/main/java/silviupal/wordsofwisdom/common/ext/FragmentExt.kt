package silviupal.wordsofwisdom.common.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import silviupal.wordsofwisdom.presentation.activities.MainActivity

/**
 * Created by Silviu Pal on 11/17/2019.
 */
fun Fragment.initToolbar(toolbar: Toolbar) {
    val act = activity as AppCompatActivity?
    act?.let { activityObject ->
        activityObject.setSupportActionBar(toolbar)

        activityObject.supportActionBar?.let {
            it.setDisplayShowTitleEnabled(false)

            if (activityObject.supportFragmentManager.backStackEntryCount > 0 ||
                    (parentFragment != null && parentFragment!!.childFragmentManager.backStackEntryCount > 0)) {
                it.setDisplayHomeAsUpEnabled(true)
                //it.setHomeActionContentDescription(R.string.content_description_up_back)
            } else if (activityObject !is MainActivity) {
                it.setDisplayHomeAsUpEnabled(true)
                //it.setHomeActionContentDescription(R.string.content_description_up_close)
            }
        }
    }
}

fun FragmentTransaction.hideOldFragment(activity: androidx.fragment.app.FragmentActivity): FragmentTransaction {
    val fragmentList = activity.supportFragmentManager.fragments
    fragmentList.filter { fragment -> fragment.isVisible }
            .forEach { fragment -> hide(fragment) }
    return this
}