package com.eventosappteste.appeventos.Servicos

import com.eventosappteste.appeventos.Model.Evento
import com.eventosappteste.appeventos.Servicos.UiServices.toDateFormatted

fun getObjectToShare(evento: Evento): String{
    return evento.title + "\n\n" +
            evento.description + "\n\n" +
            "Data: " + evento.date?.toDateFormatted() + "\n" +
            "Preço: R$" + evento.price
}