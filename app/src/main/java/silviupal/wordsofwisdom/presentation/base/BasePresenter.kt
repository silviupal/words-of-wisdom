package silviupal.wordsofwisdom.presentation.base

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Silviu Pal on 11/10/2019.
 */
abstract class BasePresenter<T> {
    protected val disposable = CompositeDisposable()

    abstract var view: T?

    abstract fun attach(view: T)

    fun detach() {
        disposable.clear()
        view = null
    }
}