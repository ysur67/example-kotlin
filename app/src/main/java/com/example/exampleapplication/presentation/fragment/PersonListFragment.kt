package com.example.exampleapplication.presentation.fragment

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.exampleapplication.ExampleApp
import com.example.exampleapplication.databinding.FragmentPersonListBinding
import com.example.exampleapplication.di.ViewModelFactory
import com.example.exampleapplication.domain.implementation.PersonViewModel
import com.example.exampleapplication.domain.implementation.PersonViewModel_Factory
import com.example.exampleapplication.presentation.adapter.PersonAdapter
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [PersonListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PersonListFragment : Fragment() {
    private var _binding: FragmentPersonListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: PersonAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: PersonViewModel by activityViewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as ExampleApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment.
         *
         * @return A new instance of fragment PersonListFragment.
         */
        @JvmStatic
        fun newInstance() = PersonListFragment()
    }
}