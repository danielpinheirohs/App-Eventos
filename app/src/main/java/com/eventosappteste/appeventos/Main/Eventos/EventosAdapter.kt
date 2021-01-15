package com.eventosappteste.appeventos.Main.Eventos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.eventosappteste.appeventos.Model.Evento
import com.eventosappteste.appeventos.R
import com.eventosappteste.appeventos.Servicos.UiServices.toDateFormatted

class EventosAdapter() : RecyclerView.Adapter<EventosAdapter.EventosViewHolder>(){

    private var events: List<Evento> = ArrayList()
    private var callback: (item: Evento)->Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, view: Int): EventosViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.evento_item, parent, false)
        return EventosViewHolder(itemView)
    }

    override fun getItemCount() = events.count()

    fun submitList(eventos: List<Evento>) {
        events = eventos
        notifyDataSetChanged()
    }

    fun setCallback(callback: (item: Evento) -> Unit) {
        this.callback = callback
    }

    override fun onBindViewHolder(viewHolder: EventosViewHolder, position: Int) {
        viewHolder.bindView(events[position]){
            val item = events[it]
            callback(item)
        }
    }

    class EventosViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        private val image = itemView.findViewById<ImageView>(R.id.card_image_perfil)
        private val title = itemView.findViewById<TextView>(R.id.card_text_titulo)
        private val subtitle = itemView.findViewById<TextView>(R.id.card_text_subtitulo)
        private val description = itemView.findViewById<TextView>(R.id.card_text_descricao)
        private var function: (index: Int) -> Unit = {}

        init {
            itemView.setOnClickListener {
                val position = adapterPosition.toInt()
                function(position)
            }
        }

        fun bindView(event: Evento, callback: (position: Int)->Unit) {

            function = callback
            image.load(event.image){
                placeholder(R.drawable.image_not_found)
                error(R.drawable.image_not_found)
            }
            title.text = event.title
            subtitle.text = event.date?.toDateFormatted()
            description.text = event.description

        }

    }
}