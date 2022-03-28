package com.example.todoapp.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.todoapp.ui.today.TodayFragment

class TodoViewPageAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val listOfFragments = listOf<Fragment>(TodayFragment())
    override fun getItemCount(): Int = listOfFragments.size

    override fun createFragment(position: Int): Fragment = listOfFragments[position]

}