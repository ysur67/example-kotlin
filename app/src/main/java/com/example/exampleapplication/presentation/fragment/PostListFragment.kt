package com.example.exampleapplication.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exampleapplication.ExampleApp
import com.example.exampleapplication.R
import com.example.exampleapplication.data.model.Post
import com.example.exampleapplication.databinding.FragmentPostListBinding
import com.example.exampleapplication.di.ViewModelFactory
import com.example.exampleapplication.domain.implementation.PersonViewModel
import com.example.exampleapplication.presentation.adapter.PostAdapter
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [PostListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostListFragment : Fragment() {
    private var _binding: FragmentPostListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: PersonViewModel by activityViewModels { viewModelFactory }

    private lateinit var adapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity?.application as ExampleApp).appComponent.inject(this)
        _binding = FragmentPostListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = PostAdapter(viewModel.posts.value ?: ArrayList())
        binding.recyclerView.adapter = adapter
        viewModel.posts.observe(viewLifecycleOwner, {
            if (it == null) return@observe
            adapter.add(it as List<Post>)
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment.
         *
         * @return A new instance of fragment PostListFragment.
         */
        @JvmStatic
        fun newInstance() = PostListFragment()
    }
}