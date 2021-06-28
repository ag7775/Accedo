package com.shivam.kotlin.colormemory_accedo.ui.screens

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.shivam.kotlin.colormemory_accedo.R
import com.shivam.kotlin.colormemory_accedo.viewmodels.GameViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class FragmentSplash : Fragment() {

    private val viewModel : GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageView>(R.id.play).setOnClickListener{
            requireActivity().startActivity(Intent(context,FragmentHighScore::class.java))
        }

        view.findViewById<ImageView>(R.id.logo).setOnClickListener{
            viewModel.saveScore(getRandomName())
        }
    }

    fun getRandomName() : String{
        return Random(1000).nextInt().toString()
    }
}