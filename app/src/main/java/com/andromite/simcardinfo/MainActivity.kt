package com.andromite.simcardinfo

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var status = ContextCompat.checkSelfPermission(this@MainActivity, android.Manifest.permission.READ_PHONE_STATE)
        if (status == PackageManager.PERMISSION_GRANTED)
        {
            assignFragment()
        } else {
            ActivityCompat.requestPermissions(this@MainActivity,arrayOf(android.Manifest.permission.READ_PHONE_STATE),123)
        }
    }

    fun assignFragment(){
        val adapter= ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(SimFragment(), "Sim 1")
        adapter.addFragment(SimFragmentTwo(), "Sim 2")
        vpager.adapter=adapter
        tabs.setupWithViewPager(vpager)

       // vpager.addOnPageChangeListener()
    }

    class ViewPagerAdapter(manager: FragmentManager): FragmentPagerAdapter(manager){

        private val fragmentList: MutableList<Fragment> = ArrayList() // storing fragment in a list
        private val titleList: MutableList<String> = ArrayList()      // storing title in a list


        //need to return the fragment
        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        //need to return size of adapter
        override fun getCount(): Int {
            return fragmentList.size
        }


        fun addFragment(fragment: Fragment, title:String){
            fragmentList.add(fragment)
            titleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if (grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
//            deviceInfo(0)
            assignFragment()

        }else{
            Toast.makeText(this@MainActivity, "Allow Permission To Continue", Toast.LENGTH_SHORT).show()
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

        }

