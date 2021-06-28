package com.shivam.kotlin.colormemory_accedo.ui.screens


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.OnBackPressedCallback
import androidx.core.view.setMargins
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.google.android.material.shape.CornerFamily
import com.shivam.kotlin.colormemory_accedo.R
import com.shivam.kotlin.colormemory_accedo.databinding.FragmentGameBinding
import com.shivam.kotlin.colormemory_accedo.viewmodels.GameViewModel
import com.shivam.kotlin.colormemory_accedo.widgets.FlipImageView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentGame : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private val gameViewModel: GameViewModel by viewModels()
    private val isGameOver = MutableLiveData(false)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        })

        binding.highScore.setOnClickListener {
            moveToHighScoreScreen()
        }

        isGameOver.observe(viewLifecycleOwner){
            if (it){
                gameViewModel.saveScore("Shivam")
                moveToHighScoreScreen()
            }
        }

        gameViewModel.currentGameScore.observe(viewLifecycleOwner) {
            binding.currentScore.text = it.toString()
        }

        gameViewModel.cardCount.observe(viewLifecycleOwner) {
            if (it == 0){
                isGameOver.value = true
            }
        }

        onStartM()
    }

    private fun onStartM() {
        val layout = binding.layoutG
        layout.post {
            createLayout(layout, 2 )
            animateCardFrame()
        }
    }

    private fun createLayout(layout: FrameLayout, count: Int) {

        // Run it and see
        val square = kotlin.math.sqrt(2.0.times(count)).toInt()

        val cellHeight: Float = layout.height.div(square.toFloat())
        val cellWidth: Float = layout.width.div(square.toFloat())
        val items =
                arrayOf(
                        R.drawable.colour1,
                        R.drawable.colour2,
//                        R.drawable.colour3,
//                        R.drawable.colour4,
//                        R.drawable.colour5,
//                        R.drawable.colour6,
//                        R.drawable.colour7,
//                        R.drawable.colour8,
                ).flatMap { arrayListOf(it, it) }.shuffled().toMutableList()
        for (row in 0 until square) {
            for (col in 0 until square) {
                val item = items.removeFirst()
                createFlipView(cellWidth.toInt(), cellHeight.toInt(), layout)
                        .apply {
                            setImageResource(item)
                            puzzleId = item
                            rotationY = 0f
                            setOnClickListener {
                                post { handleClick(this) }
                            }
                            animate().x(col * cellWidth - 4).y(row * cellHeight - 4).setDuration(0).start()
                        }

            }
        }

    }

    private fun moveToHighScoreScreen() {
        requireActivity().startActivity(Intent(context,FragmentHighScore::class.java))
//        findNavController().navigate(R.id.action_fragmentGamMe_to_fragmentHighScore)
    }

    private fun createFlipView(width: Int, height: Int, parent: FrameLayout) = FlipImageView(context).apply {
        val lp = FrameLayout.LayoutParams(width - 8, height - 8).apply {
            setMargins(4)
        }
        parent.addView(this, lp)
        setBackgroundResource(R.drawable.card_bg)
        imageAlpha = 0
        shapeAppearanceModel = shapeAppearanceModel
                .toBuilder()
                .setAllCorners(CornerFamily.CUT, 8f * resources.displayMetrics.density)
                .build()
    }

    @Synchronized
    private fun handleClick(view: FlipImageView) {
        gameViewModel.handleClick(view)
    }

    private fun animateCardFrame() {
        binding.layoutG.animate().scaleX(1f).scaleY(1f).setDuration(500).start()
    }

    private fun showExitGameDialog() {
        AlertDialog.Builder(context).apply {
            setTitle("Exit Game")
            setMessage("Are you sure want to exit?")
            setPositiveButton("Yes") { dialog, which ->
                moveToSplashScreen()
            }
            setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }
            show()
        }
    }

    private fun moveToSplashScreen() {
        findNavController().navigateUp()
    }

    fun onBackPressed(){
        if (gameViewModel.currentGameScore.value!! != 0 ){
            showExitGameDialog()
        }else{
            moveToSplashScreen()
        }
    }
}