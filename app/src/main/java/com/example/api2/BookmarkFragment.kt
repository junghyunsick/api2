package com.example.api2

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.api2.databinding.FragmentBookmarkBinding


class BookmarkFragment : Fragment() {

    private lateinit var mContext: Context

    private var binding : FragmentBookmarkBinding? = null
    private lateinit var adapter: BookmarkAdapter

    private var likedItems: List<SearchItemModel> = listOf()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mainActivity = activity as MainActivity
        likedItems = mainActivity.likedItems
        // Inflate the layout for this fragment

        adapter = BookmarkAdapter(mContext).apply {
            items = likedItems.toMutableList()
        }
        binding = FragmentBookmarkBinding.inflate(inflater, container, false).apply {
            rvBookmark.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            rvBookmark.adapter = adapter
        }
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}