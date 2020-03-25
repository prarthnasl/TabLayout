package com.prarthna.tablayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.prarthna.tablayout.ui.adapter.FragmentTabAdapter
import com.prarthna.tablayout.R

class HomeFragment : Fragment() {

    private lateinit var fragmentTabAdapter: FragmentTabAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val activity : AppCompatActivity = activity as AppCompatActivity
        val fragmentView = requireNotNull(view) {"View should not be null when calling onActivityCreated"}

        val toolbar: Toolbar = fragmentView.findViewById(R.id.toolbar)
        activity.setSupportActionBar(toolbar)

        fragmentTabAdapter = FragmentTabAdapter(this)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = fragmentTabAdapter

        tabLayout = view.findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "TAB ${(position + 1)}"

            if (position == 0) {
                tab.view.setBackgroundResource(R.drawable.tab_start_selector);
            } else if (position == fragmentTabAdapter.itemCount - 1) {
                tab.view.setBackgroundResource(R.drawable.tab_end_selector);
            } else {
                tab.view.setBackgroundResource(R.drawable.tab_center_selector);
            }

        }.attach()
    }
}