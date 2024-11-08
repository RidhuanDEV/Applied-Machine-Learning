package com.dicoding.asclepius.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.data.Result
import com.dicoding.asclepius.data.response.Article
import com.dicoding.asclepius.databinding.FragmentArticleBinding


class ArticleFragment : Fragment() {
    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding
    private val viewModel: HistoryViewModel by viewModels {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return (binding?.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchNews()
        val articleAdapter = ArticleAdapter()
        binding?.rvArticle?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = articleAdapter
        }

        viewModel.eventDetail.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    binding?.progressBar?.visibility = View.VISIBLE
                }

                is Result.Success -> {
                    binding?.progressBar?.visibility = View.GONE

                    result.data.let { event ->
                        val items = arrayListOf<Article>()
                        event.map {
//                            Log.d("Mapped Data", "Title: ${it.title}, Description: ${it.description}")
                            val item = Article(
                                description = it.description,
                                publishedAt = it.publishedAt,
                                title = it.title,
                                urlToImage = it.urlToImage
                            )
                            items.add(item)
                        }
                        articleAdapter.submitList(items)
                    }
                }
                    is Result.Error -> {
                    binding?.progressBar?.visibility = View.GONE
                    Toast.makeText(requireContext(), result.error, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}