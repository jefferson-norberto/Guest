package com.example.guests.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guests.databinding.FragmentAllGuestsBinding
import com.example.guests.service.constants.GuestConstants
import com.example.guests.view.adapter.GuestAdapter
import com.example.guests.view.listener.GuestListener
import com.example.guests.viewmodel.AllGuestsViewModel
import kotlinx.android.synthetic.main.fragment_all_guests.view.*

class AllGuestsFragment : Fragment() {

    private lateinit var allGuestsViewModel: AllGuestsViewModel
    private var _binding: FragmentAllGuestsBinding? = null
    private val mAdapter = GuestAdapter()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var mGuestListener: GuestListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        allGuestsViewModel = ViewModelProvider(this).get(AllGuestsViewModel::class.java)

        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        val root: View = binding.root

        //RecyclerView - faz listagem de elementos
        // 1 - Obter a recycle
        val recycle = binding.root.recycle_all_guests

        // 2 - Definir um layout de comportamento (não é a exibição)
        recycle.layoutManager = LinearLayoutManager(context)

        // 3 - Definir um adapter - juntar os dados do repositorio e aplicar na exibição
        recycle.adapter = mAdapter

        //dessa forma consigo ignorar o construtor da classe
        //e a minha fragment está lidando com o listener
        //passando ele para o adapter
        mGuestListener = object : GuestListener{
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)

                //variável usada para passar dados de uma activity para outra
                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUESTID, id)

                //inserindo os dados na intent para passar
                intent.putExtras(bundle)
                startActivity(intent)
            }

        }

        mAdapter.attachListener(mGuestListener)

        observer()


        return root
    }

    //uso o onResume para quando retornar o foco a minha activity ela atualize a lista novamente
    override fun onResume() {
        super.onResume()
        allGuestsViewModel.load()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observer(){
        allGuestsViewModel.guestList.observe(viewLifecycleOwner, Observer {
            //neste caso it é a loista de usuário pegado
            mAdapter.updateGuests(it)
        })
    }
}