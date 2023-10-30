package com.frey.msu.criminalintent

import android.os.Bundle
import android.util.Log
import android.util.Log.DEBUG
import android.view.AbsSavedState
import android.view.ContextMenu
import android.view.View
import androidx.core.app.BundleCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

private const val TAG = "CrimeListFragment"

class CrimeListFragment : Fragment() {

    private val crimeListViewModel :CrimeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "total Crimes : ${crimeListViewModel.crimes.size}")
    }

}