package com.geekbrains.homebooking.ui.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.homebooking.R
import com.geekbrains.homebooking.databinding.FragmentCitiesBinding
import com.geekbrains.homebooking.databinding.FragmentStartBinding
import com.geekbrains.homebooking.model.AppState
import com.geekbrains.homebooking.model.City
import com.geekbrains.homebooking.ui.hotels.HotelsFragment
import com.geekbrains.weatherwithmvvm.model.interfaces.OnItemViewClickListener

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() =
            StartFragment()
    }
}