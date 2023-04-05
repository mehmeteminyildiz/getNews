package com.mey.getnews.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mey.getnews.adapter.NewsAdapter
import com.mey.getnews.data.model.Articles
import com.mey.getnews.databinding.FragmentHomeBinding
import com.mey.getnews.util.Constants
import com.mey.getnews.util.Status
import com.mey.getnews.viewmodel.GetNewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private var TOTAL: Int? = null
    lateinit var viewModel: GetNewsViewModel

    private val adapter = NewsAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(GetNewsViewModel::class.java)
        handleClickEvents()
        getNews()
        observeNews()
    }

    private fun observeNews() {
        viewModel.news.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {

                    it.data?.let { data ->
                        when (data.status) {
                            "ok" -> {
                                TOTAL = data.totalResults
                                processNewsResponse(data = data.articles)
                            }
                            "error" -> {
                                Timber.e("error code : ${data.code}")
                                Timber.e("error message : ${data.message}")
                            }
                        }
                    }
                }
                Status.ERROR -> {}
                Status.LOADING -> {}
            }

        })
    }

    private fun processNewsResponse(data: ArrayList<Articles>?) {
        if (data.isNullOrEmpty()) {
            Timber.e("data is Null or Empty!")
        } else {
            binding.apply {
                rvNews.adapter = adapter
                rvNews.layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                adapter.articles = data
            }

            for (new in data) {
                Timber.e("title : ${new.title}")

            }
        }

    }

    private fun getNews() {
        viewModel.getNews(
            "tesla",
            page = 1,
            pageSize = 10,
            apiKey = Constants.API_KEY
        )
    }

    private fun handleClickEvents() {
        binding.apply {
            cardSort.setOnClickListener {

            }
            cardFilter.setOnClickListener {

            }
        }
    }

}