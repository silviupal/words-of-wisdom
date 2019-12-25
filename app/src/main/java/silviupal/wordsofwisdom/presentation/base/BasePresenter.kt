package silviupal.wordsofwisdom.presentation.base

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Silviu Pal on 11/10/2019.
 */
abstract class BasePresenter<T> {
    protected val disposable = CompositeDisposable()

    abstract var view: T?

    fun attach(view: T) {
        if (this.view == null) {
            this.view = view
        }
    }

    fun detach() {
        disposable.clear()
        view = null
    }
}