package com.example.exampleapplication.presentation.fragment

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
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

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: PersonViewModel by activityViewModels { viewModelFactory }

    private lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as ExampleApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostListBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = PostAdapter(viewModel.posts.value ?: ArrayList())
        binding.recyclerView.adapter = adapter
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.updatePosts()
        }

        viewModel.loadPosts()
        viewModel.posts.observe(viewLifecycleOwner, {
            if (it == null || it.size == 0) {
                togglePostList(false)
            } else {
                togglePostList(true)
                adapter.add(it as List<Post>)
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, {
            binding.swipeRefresh.isRefreshing = it
        })
    }

    private fun togglePostList(postListHasItems: Boolean) {
        if (postListHasItems) {
            binding.recyclerView.isEnabled = true
            binding.postListEmpty.visibility = View.GONE
        } else {
            binding.recyclerView.isEnabled = false
            binding.postListEmpty.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.search_menu, menu)
        val item = menu.findItem(R.id.bar_search)
        val searchView = item.actionView as SearchView
        searchView.isSubmitButtonEnabled = true

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(value: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                query ?: return true
                viewModel.searchPost(query)
                return true
            }
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