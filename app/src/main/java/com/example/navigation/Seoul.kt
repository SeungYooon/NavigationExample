package com.example.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_seoul.*

class Seoul : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_seoul, container, false)

        view.findViewById<Button>(R.id.to_busan_from_seoul).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_global_busan_screen)
        }
        view.findViewById<Button>(R.id.to_daegu_from_seoul).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_global_daegu_screen)
        }
        return view
    }
}