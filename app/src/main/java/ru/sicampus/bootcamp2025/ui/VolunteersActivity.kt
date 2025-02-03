package ru.sicampus.bootcamp2025.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.ActivityVolunteersBinding
import ru.sicampus.bootcamp2025.ui.vlist.ActiveVolunteersListFragment
import ru.sicampus.bootcamp2025.ui.vlist.FreeVolunteersListFragment

class VolunteersActivity : AppCompatActivity() {

    private lateinit var binding : ActivityVolunteersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVolunteersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.freeVolunteers.setOnClickListener{freeBtnClick()}
        binding.activeVolunteers.setOnClickListener{activeBtnClick()}
        binding.menuProfile.setOnClickListener{toProfile()}
    }

    private fun toProfile() {
        val intent = Intent(this@VolunteersActivity, ProfileActivity::class.java)
        startActivity(intent)
    }

    private fun freeBtnClick(){
            invertButtons(true)

    }
    private fun activeBtnClick(){
            invertButtons(false)

    }
    private fun invertButtons(isInverted : Boolean){
        binding.freeVolunteers.isClickable = !isInverted
        binding.activeVolunteers.isClickable = isInverted
        binding.freeVolunteers.setBackgroundResource(if(isInverted)  R.drawable.btn_free_shape_on else R.drawable.btn_free_shape)
        binding.activeVolunteers.setBackgroundResource(if(isInverted)  R.drawable.btn_active_shape else R.drawable.btn_active_shape_on)
        switchFragments(if(isInverted) FreeVolunteersListFragment() else ActiveVolunteersListFragment())

    }
    private fun switchFragments(f : Fragment){
        supportFragmentManager.commit{
            replace(R.id.listContent, f )
            addToBackStack(null)
        }


    }
}