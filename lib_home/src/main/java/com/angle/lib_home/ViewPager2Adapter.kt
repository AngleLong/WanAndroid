package com.angle.lib_home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * ViewPager2的适配器
 */
class ViewPager2Adapter(fragmentActivity: FragmentActivity, private val fragments: List<Fragment>) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}