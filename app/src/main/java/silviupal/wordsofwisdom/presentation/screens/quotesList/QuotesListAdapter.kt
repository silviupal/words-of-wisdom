package silviupal.wordsofwisdom.presentation.screens.quotesList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_quote.view.*
import silviupal.wordsofwisdom.R
import silviupal.wordsofwisdom.domain.model.QuoteModel

/**
 * Created by Silviu Pal on 11/17/2019.
 */
class QuotesListAdapter(private val context: Context,
                        private val showEditQuotePage: (QuoteModel) -> Unit) : RecyclerView.Adapter<QuotesListAdapter.ViewHolder>() {
    private val list: ArrayList<QuoteModel> = arrayListOf()
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(inflater.inflate(R.layout.item_quote, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setList(quotesList: ArrayList<QuoteModel>) {
        list.clear()
        list.addAll(quotesList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(quote: QuoteModel) {
            itemView.tvQuoteText.text = quote.quoteText
            itemView.tvQuoteAuthor.text = quote.author ?: ""
            itemView.setOnClickListener { showEditQuotePage(quote) }
            // TODO set dynamic font
            // TODO set background image
        }
    }
}

