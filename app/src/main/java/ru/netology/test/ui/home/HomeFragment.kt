package ru.netology.test.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ru.netology.test.OnInteractionListener
import ru.netology.test.Post
import ru.netology.test.PostAdapter
import ru.netology.test.R
import ru.netology.test.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val adapter = PostAdapter(object : OnInteractionListener {

        })

        adapter.registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                if (positionStart == 0) {
                    binding.posts.smoothScrollToPosition(0)
                }
            }
        })

        binding.posts.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) {data ->
            adapter.submitList(data.posts)
        }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            if (state.error) {
                Snackbar.make(binding.root, "Ошибка", Snackbar.LENGTH_LONG)
                    .setAction("повторить") {
                        viewModel.loadPosts()
                    }
                    .show()
            }
        }

        return binding.root
    }
}