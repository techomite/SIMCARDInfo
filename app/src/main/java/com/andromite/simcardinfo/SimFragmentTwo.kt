package com.andromite.simcardinfo


import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_sim.view.*


class SimFragmentTwo : Fragment() {

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        // imei, serialNo, **simCOuntryISO, **operatorName,
        // **sim status, **mobile Number, gsm/cdma

        var i=1
        var view=inflater.inflate(R.layout.fragment_sim, container, false)


        val tm = activity!!.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val sub = activity!!.getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE) as SubscriptionManager

        view.mobileText.text="Your carrier stores your mobile number on their central system not on the sim"



        view.imei.text="${tm.getImei(i)}"
        var serialNumber= sub.getActiveSubscriptionInfoForSimSlotIndex(i)?.iccId
        var operator_Name= sub.getActiveSubscriptionInfoForSimSlotIndex(i)?.displayName
        var country_ISO=sub.getActiveSubscriptionInfoForSimSlotIndex(i)?.countryIso



        if (serialNumber==null){
            view.serialNo.text="No Sim Card"
            view.operatorName.text="No Sim Card"
            view.simCountryISO.text="No Sim Card"
        }else{
            view.serialNo.text="${sub.getActiveSubscriptionInfoForSimSlotIndex(i)?.iccId}"
            view.operatorName.text="${sub.getActiveSubscriptionInfoForSimSlotIndex(i)?.displayName}"
            view.simCountryISO.text="${sub.getActiveSubscriptionInfoForSimSlotIndex(i)?.countryIso}"
        }

        if (country_ISO.equals("in")){
            view.simCountryISO.text="India"
        }else if(country_ISO.equals("qa")){
            view.simCountryISO.text="Qatar"
        }
        return view

    }

}
