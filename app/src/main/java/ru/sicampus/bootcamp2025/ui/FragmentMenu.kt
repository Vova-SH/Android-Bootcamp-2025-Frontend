package ru.sicampus.bootcamp2025.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.sicampus.bootcamp2025.ListActivity
import ru.sicampus.bootcamp2025.MainActivity
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.FragmentMenuBinding

class FragmentMenu:Fragment(R.layout.fragment_menu) {
    lateinit var binding: FragmentMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.menu.setOnClickListener {
            openListActivity()
        }
    }

    private fun openListActivity(){
        val intent: Intent = Intent(context,ListActivity::class.java)
        startActivity(intent)

    }



    companion object{
        @JvmStatic
        fun newInstance() = FragmentMenu()
    }

}