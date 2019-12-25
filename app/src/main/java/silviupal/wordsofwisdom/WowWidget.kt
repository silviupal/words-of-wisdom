package silviupal.wordsofwisdom

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.RemoteViews
import android.widget.Toast
import io.reactivex.disposables.CompositeDisposable
import silviupal.wordsofwisdom.common.ext.app
import silviupal.wordsofwisdom.domain.model.QuoteModel


/**
 * Created by Silviu Pal on 12/21/2019.
 */
class WowWidget : AppWidgetProvider() {
    companion object {
        const val PREFS_POSITION = "quote_position"
    }

    private var disposable = CompositeDisposable()
    private var quotesList: ArrayList<QuoteModel> = arrayListOf()

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        Toast.makeText(context, "Widget updated", Toast.LENGTH_SHORT).show()

        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            getQuoteList(context, appWidgetManager, appWidgetId)
        }
    }

    private fun getQuoteList(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        disposable.add(context.app.useCaseFactory.getQuoteListUseCase().build()
                .subscribe({ list ->
                    this.quotesList = list
                    updateAppWidget(context, appWidgetManager, appWidgetId)
                }, {})
        )
    }

    private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        val position: Int = getPositionList(context)

        Log.d("Silviu", "current position = $position")

        val views = RemoteViews(context.packageName, R.layout.wow_widget)
        if (quotesList.isNotEmpty() && position < quotesList.size) {
            views.setViewVisibility(R.id.quoteLayoutWidget, View.VISIBLE)
            views.setViewVisibility(R.id.emptyLayoutWidget, View.GONE)

            if (position == -1) {
                views.setTextViewText(R.id.tvQuoteTextWidget, quotesList[0].quoteText)
                views.setTextViewText(R.id.tvQuoteAuthorWidget, quotesList[0].author)
                savePositionToSharedPrefs(context, 0)
            } else {
                views.setTextViewText(R.id.tvQuoteTextWidget, quotesList[position].quoteText)
                views.setTextViewText(R.id.tvQuoteAuthorWidget, quotesList[position].author)
                savePositionToSharedPrefs(context, position)
            }
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        } else {
            views.setViewVisibility(R.id.quoteLayoutWidget, View.GONE)
            views.setViewVisibility(R.id.emptyLayoutWidget, View.VISIBLE)
            views.setOnClickPendingIntent(R.id.ivAddQuoteWidget, getOpenAppIntent(context, appWidgetId))
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

        views.setOnClickPendingIntent(R.id.tvLeft, getLeftPendingAction(context, appWidgetId, position))
        views.setOnClickPendingIntent(R.id.tvRight, getRightPendingAction(context, appWidgetId, position))
        views.setOnClickPendingIntent(R.id.widget, getOpenAppIntent(context, appWidgetId))

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    private fun getPositionList(context: Context): Int =
            context.app.sharedPrefs.getInt(PREFS_POSITION, -1)

    private fun savePositionToSharedPrefs(context: Context, position: Int) {
        context.app.sharedPrefs.edit()
                .putInt(PREFS_POSITION, position)
                .apply()
    }

    private fun getLeftPendingAction(context: Context, appWidgetId: Int, position: Int): PendingIntent {
        Log.d("Silviu", "clicked left")

        if (position == 0 && quotesList.size > 0) {
            savePositionToSharedPrefs(context, quotesList.size - 1)
        } else {
            savePositionToSharedPrefs(context, position - 1)
        }
        return getUpdateIntent(context, appWidgetId)
    }

    private fun getRightPendingAction(context: Context, appWidgetId: Int, position: Int): PendingIntent {
        Log.d("Silviu", "clicked right")

        if (position == quotesList.size - 1) {
            savePositionToSharedPrefs(context, 0)
        } else {
            savePositionToSharedPrefs(context, position + 1)
        }
        return getUpdateIntent(context, appWidgetId)
    }

    private fun getUpdateIntent(context: Context, appWidgetId: Int): PendingIntent {
        val intentUpdate = Intent(context, WowWidget::class.java)
        intentUpdate.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, IntArray(appWidgetId))
        return PendingIntent.getBroadcast(
                context, appWidgetId, intentUpdate, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun getOpenAppIntent(context: Context, appWidgetId: Int): PendingIntent {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("wordsofwisdom://open"))
        return PendingIntent.getActivity(
                context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}