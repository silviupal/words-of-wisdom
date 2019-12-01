package silviupal.wordsofwisdom.presentation.screens.quoteDetails

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_font.view.*
import silviupal.wordsofwisdom.R
import silviupal.wordsofwisdom.common.FontsFactory

/**
 * Created by Silviu Pal on 12/1/2019.
 */
class FontsAdapter(private val context: Context,
                   private val setQuoteTypeface: (Typeface, String) -> Unit) : RecyclerView.Adapter<FontsAdapter.ViewHolder>() {
    private val fonts = FontsFactory.Fonts.values()
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(inflater.inflate(R.layout.item_font, parent, false))

    override fun getItemCount(): Int = fonts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(fonts[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(font: FontsFactory.Fonts) {
            val typeface = FontsFactory(context).getTypeface(font)
            itemView.tvFontItem.typeface = typeface
            itemView.setOnClickListener {
                setQuoteTypeface(typeface, font.fontName)
            }
        }
    }
}