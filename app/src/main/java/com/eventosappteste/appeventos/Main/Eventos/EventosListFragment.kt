package com.eventosappteste.appeventos.Main.Eventos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eventosappteste.appeventos.Model.Evento
import com.eventosappteste.appeventos.R
import com.eventosappteste.appeventos.Servicos.NetworkService
import retrofit2.Call
import retrofit2.Response

class EventosListFragment : Fragment() {


    private lateinit var eventosAdapter: EventosAdapter
    lateinit var recyclerViewEventos: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventosAdapter = EventosAdapter()
        eventosAdapter.setCallback {
            val action = EventosListFragmentDirections.actionEventosListFragmentToEventoDescricaoFragment(it)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_eventos_list, container, false)
        setupButtons(view)
        getData()
        return view
    }

    fun setupButtons(view: View){

        recyclerViewEventos = view.findViewById(R.id.recyclerview_lista_eventos)

        recyclerViewEventos.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerViewEventos.setHasFixedSize(true)
        recyclerViewEventos.adapter = eventosAdapter

    }

    fun getData() {
        val retrofitClient = NetworkService
            .getRetrofitInstance()

        val callback = retrofitClient.getEvents()

        callback.enqueue(object : retrofit2.Callback<List<Evento>> {
            override fun onFailure(call: Call<List<Evento>>, t: Throwable) {
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
            }
            override fun onResponse(call: Call<List<Evento>>, response: Response<List<Evento>>) {
                eventosAdapter.submitList(response.body()!!)
            }
        })

    }
}
