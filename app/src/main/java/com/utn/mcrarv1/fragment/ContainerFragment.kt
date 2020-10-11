package com.utn.mcrarv1.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.setFragmentResult
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.utn.mcrarv1.R
import kotlinx.android.synthetic.main.fragment_tab1.*
import kotlin.contracts.contract


class ContainerFragment : Fragment() {



    lateinit var v : View
    lateinit var ViewPager : ViewPager2
    lateinit var tabLayout : TabLayout
    var userID : Int = 0
    var contactID : Int = 0
    var index : Int = 0
    var keyValue : String ="key"

    private val PREF_NAME = "TABPreferences"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_container, container, false)

        ViewPager = v.findViewById(R.id.view_pager)
        tabLayout = v.findViewById(R.id.tabulador)


        return v

    }

    override fun onStart() {
        super.onStart()


        userID = ContainerFragmentArgs.fromBundle(requireArguments()).userID
        contactID = ContainerFragmentArgs.fromBundle(requireArguments()).contactID

//        val result = contactID
//        setFragmentResult("requestKey", bundleOf("bundleKey" to result))


//        val sharedPref: SharedPreferences = v.context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
//        keyValue += index.toString()
//        index++
//        val editor = sharedPref.edit()
//        editor.putInt(keyValue, index)
//        //editor.putInt("contactID", 7)
//        editor.apply()

        ViewPager.setAdapter(createCardAdapter())


        TabLayoutMediator(tabLayout, ViewPager, TabLayoutMediator.TabConfigurationStrategy{ tab, position ->
            when (position){
                0 -> tab.setIcon(R.drawable.ic_baseline_account_box_24)
                1 -> tab.setIcon(R.drawable.ic_baseline_home_work_24)
                2 -> tab.setIcon(R.drawable.ic_baseline_book_24)
                else -> tab.text = "undefined"
            }
        }).attach()
    }


    private fun createCardAdapter():ViewPagerAdapter?{
        return ViewPagerAdapter(requireActivity(), userID, contactID)
    }

    class ViewPagerAdapter(fragmentActivity: FragmentActivity, user: Int, contact: Int) : FragmentStateAdapter(fragmentActivity)
    {

        var user :Int = user
        var contact : Int = contact

        override fun createFragment(position: Int): Fragment
        {

            return when(position){
                0 -> Tab1Fragment().newInstance(user, contact)
                1 -> Tab2ragment().newInstance(user,contact)
                2 -> Tab3Fragment().newInstance(user,contact)

                else -> Tab1Fragment()
            }

        }

        override fun getItemCount(): Int {
            return TAB_COUNT
        }


        companion object {
            private const val TAB_COUNT=3
        }



    }

}

