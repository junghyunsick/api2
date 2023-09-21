package com.example.api2

import android.app.DownloadManager.Query
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.api2.databinding.FragmentSearchBinding
import com.example.api2.retrofit_client.apiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var mContext: Context
    private lateinit var adapter: SearchAdapter
    private lateinit var gridmanager: StaggeredGridLayoutManager

    private var resItems:ArrayList<SearchItemModel> = ArrayList()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        setupViews()
        setupListeners()

        return binding.root
    }

    private fun setupViews(){
        gridmanager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvSearchResult.layoutManager = gridmanager

        adapter = SearchAdapter(mContext)
        binding.rvSearchResult.adapter = adapter
        binding.rvSearchResult.itemAnimator = null

       val lastSearch = Utils.getLastSearch(requireContext())
        binding.etSearch.setText(lastSearch)

    }
    private fun setupListeners(){
        binding.tvSearch.setOnClickListener {
            val query = binding.etSearch.text.toString()
            if (query.isNotEmpty()){
                Utils.saveLastSearch(requireContext(), query)
                adapter.clearItem()
                fetchImageResults(query)
            }else{
                Toast.makeText(mContext, "검색어를 입력해 주세요.", Toast.LENGTH_SHORT).show()
            }

            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE)as InputMethodManager
            imm.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
        }
    }

    private fun fetchImageResults(query: String){
        apiService.image_search(Constants.AUTH_HEADER, query, "recency", 1, 80)?.enqueue(object : Callback<ImageModel?>{
            override fun onResponse(call: Call<ImageModel?>, response: Response<ImageModel?>) {
                response.body()?.meta?.let { meta ->
                    if (meta.totalCount > 0){
                        response.body()!!.documents.forEach{document ->
                            val title = document.displaySitenames
                            val datetime = document.datetime
                            val url = document.thumbnailUrl
                            resItems.add(SearchItemModel(title, datetime, url))
                        }
                    }
                }
                adapter.items = resItems
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ImageModel?>, t: Throwable) {

            }
        })
    }
}