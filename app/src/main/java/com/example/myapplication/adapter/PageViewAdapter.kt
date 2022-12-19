package com.example.myapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myapplication.fragments.CallsFragment
import com.example.myapplication.fragments.ChatFragment
import com.example.myapplication.fragments.StatusFragment

class PageViewAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = ChatFragment()
            }
            1 -> {
                fragment = CallsFragment()
            }
            2 -> {
                fragment = StatusFragment()
            }
        }
        return fragment!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        when (position) {
            0 -> {
                title = "Chats"
            }
            1-> {
                title = "Calls"
            }
            2-> {
                title = "Status"
            }
        }
        return title!!
    }
}