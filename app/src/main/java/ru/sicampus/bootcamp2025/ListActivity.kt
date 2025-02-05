package ru.sicampus.bootcamp2025

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.sicampus.bootcamp2025.ui.FragmentList

class ListActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_activity)
        openFragment(R.id.list_fragment,FragmentList.newInstance())

    }

    private fun openFragment(id:Int,fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(id,fragment).commit()
    }

}