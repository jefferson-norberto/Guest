package com.example.guests.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guests.databinding.FragmentAllGuestsBinding
import com.example.guests.databinding.FragmentPresentsBinding
import com.example.guests.service.constants.GuestConstants
import com.example.guests.view.adapter.GuestAdapter
import com.example.guests.view.listener.GuestListener
import com.example.guests.viewmodel.GuestsViewModel
import kotlinx.android.synthetic.main.fragment_all_guests.view.*
import kotlinx.android.synthetic.main.fragment_presents.view.*

class PresentsFragment : Fragment() {
    private lateinit var mViewModel: GuestsViewModel
    private lateinit var mGuestListener: GuestListener
    private val mAdapter = GuestAdapter()
    private var _binding: FragmentPresentsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        mViewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)

        _binding = FragmentPresentsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recycle = binding.root.recycle_presents
        recycle.layoutManager = LinearLayoutManager(context)
        recycle.adapter = mAdapter

        mGuestListener = object : GuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUESTID, id)

                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load(GuestConstants.FILTER.PRESENT)
            }

        }

        mAdapter.attachListener(mGuestListener)

        observer()

        return root
    }
    override fun onResume() {
        super.onResume()
        mViewModel.load(GuestConstants.FILTER.PRESENT)
    }

    private fun observer(){
        mViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}