package com.example.api2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import com.example.api2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var likedItems: ArrayList<SearchItemModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            btnSearchFragment.setOnClickListener {
                setFragment(SearchFragment())
            }
            btnBookmarkFragment.setOnClickListener {
                setFragment(BookmarkFragment())
            }
        }

        setFragment(SearchFragment())
    }
    private fun setFragment(frag : Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout, frag)
            setReorderingAllowed(true)
            addToBackStack(null)
        }.commit()
    }

    fun addLikedItem(item: SearchItemModel){
        if (!likedItems.contains(item)){
            likedItems.add(item)
        }
    }

    fun removeLikedItem(item: SearchItemModel){
        likedItems.remove(item)
    }

}