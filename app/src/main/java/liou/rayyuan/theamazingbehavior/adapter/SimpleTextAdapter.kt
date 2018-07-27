package liou.rayyuan.theamazingbehavior.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import liou.rayyuan.theamazingbehavior.R

class SimpleTextAdapter : RecyclerView.Adapter<SimpleTextAdapter.SimpleTextViewHolder>() {

    private val texts = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleTextViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return SimpleTextViewHolder(view)
    }

    override fun getItemCount(): Int = texts.size

    override fun onBindViewHolder(holder: SimpleTextViewHolder, position: Int) {
        val adapterPosition = holder.adapterPosition
        if (adapterPosition != RecyclerView.NO_POSITION) {
            holder.textTitle.text = texts[adapterPosition]
        }
    }

    fun addText(vararg texts: String) {
        val originalPosition = itemCount
        this.texts.addAll(texts)
        notifyItemRangeInserted(originalPosition, texts.size)
    }

    inner class SimpleTextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val textTitle: TextView = itemView.findViewById(R.id.item_list_title)
    }
}