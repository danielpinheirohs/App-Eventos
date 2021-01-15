package com.eventosappteste.appeventos.Main.Eventos

import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.eventosappteste.appeventos.Model.CheckIn
import com.eventosappteste.appeventos.Model.Evento
import com.eventosappteste.appeventos.R
import com.eventosappteste.appeventos.Servicos.NetworkService
import com.eventosappteste.appeventos.Servicos.UiServices.toDateFormatted
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class EventoDescricaoFragment : Fragment() {

    lateinit var image: ImageView
    lateinit var titulo: TextView
    lateinit var compartilharButton: ImageView
    lateinit var data: TextView
    lateinit var preco: TextView
    lateinit var descricao: TextView
    lateinit var checkinButton: MaterialButton
    lateinit var toolBar: MaterialToolbar
    private lateinit var customAlertDialogView : View
    private lateinit var materialAlertDialogBuilder: MaterialAlertDialogBuilder

    private val args by navArgs<EventoDescricaoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_evento_descricao, container, false)
        setupbuttons(view)
        return view
    }

    private fun setupbuttons(view: View){
        image = view.findViewById(R.id.eventodescricao_imagem)
        titulo = view.findViewById(R.id.eventodescricao_titulo)
        compartilharButton = view.findViewById(R.id.eventodescricao_compartilhar)
        data = view.findViewById(R.id.eventodescricao_data)
        preco = view.findViewById(R.id.eventodescricao_preco)
        descricao = view.findViewById(R.id.eventodescricao_descricao)
        checkinButton = view.findViewById(R.id.eventodescricao_checkin)
        toolBar = view.findViewById(R.id.topAppBar)
        materialAlertDialogBuilder = MaterialAlertDialogBuilder(requireContext())

        image.load(args.evento.image)
        titulo.text = args.evento.title
        data.text = args.evento.date?.toDateFormatted()
        preco.text = args.evento.price.toString()
        descricao.text = args.evento.description

        toolBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        compartilharButton.setOnClickListener{
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Compartilhar")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }


        checkinButton.setOnClickListener{
            customAlertDialogView = LayoutInflater.from(requireContext())
                .inflate(R.layout.dialog, null, false)

            // Launching the custom alert dialog
            launchCustomAlertDialog()
        }
    }

    private fun launchCustomAlertDialog() {
        val nameTextField = customAlertDialogView.findViewById<TextInputEditText>(R.id.tieName)
        val emailTextField = customAlertDialogView.findViewById<TextInputEditText>(R.id.tieEmail)

        // Building the Alert dialog using materialAlertDialogBuilder instance
        materialAlertDialogBuilder.setView(customAlertDialogView)
            .setPositiveButton("Confirmar") { dialog, _ ->
                val eventId = args.evento.id
                val name = nameTextField.text.toString()
                val email = emailTextField.text.toString()

                val retrofitClient = NetworkService
                    .getRetrofitInstance("https://5f5a8f24d44d640016169133.mockapi.io/api/")

                val checkIn = CheckIn(
                    eventId,
                    name,
                    email
                )

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val result = retrofitClient.postCheckIn(checkIn).execute()

                        if (result.isSuccessful && result.code() == 201) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(requireContext(), "deu certo", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(requireContext(), "deu errado", Toast.LENGTH_LONG).show()
                            }
                        }
                    } catch (ex: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(requireContext(), ex.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                }

                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}