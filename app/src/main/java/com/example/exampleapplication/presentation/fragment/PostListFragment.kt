package com.example.exampleapplication.presentation.fragment

import android.opengl.Visibility
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
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

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: PersonViewModel by activityViewModels { viewModelFactory }

    private lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        (activity?.application as ExampleApp).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = PostAdapter(viewModel.posts.value ?: ArrayList())
        binding.recyclerView.adapter = adapter
        viewModel.loadPosts()
        viewModel.posts.observe(viewLifecycleOwner, {
            adapter.clearDataSet()
            if (it == null || it.size == 0) {
                togglePostList(false)
            } else {
                togglePostList(true)
                adapter.add(it as List<Post>)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.optionUpdate -> {
                adapter.clearDataSet()
                viewModel.updatePosts()
            }
        }
        return super.onOptionsItemSelected(item)
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