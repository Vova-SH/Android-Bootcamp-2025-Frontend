package ru.sicampus.bootcamp2025.ui.entry.signup

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.databinding.SignupFragmentBinding
import ru.sicampus.bootcamp2025.ui.mainscreen.MainActivity

class SignUpFragment : Fragment() {

    private lateinit var binding: SignupFragmentBinding

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SignupFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = NavHostFragment.findNavController(this)

        binding.toLogin.setOnClickListener {
            navController.navigate(R.id.action_signup_fragment_to_login_fragment)
        }
        binding.process.setOnClickListener {
            startActivity(Intent(activity, MainActivity::class.java))
        }
    }
}