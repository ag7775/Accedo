package com.shivam.kotlin.colormemory_accedo.widgets

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.google.android.material.imageview.ShapeableImageView

class FlipImageView: ShapeableImageView {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    var puzzleId: Int = -1
    var maskImage: Int = 0
    var puzzleImage: Int = 0

    // leave it for now
    fun reveal(onComplete: () -> Unit) {
        val revealAnim = animate().rotationY(180f)
                .setUpdateListener {
                    val a = it.animatedFraction
                    if (a > 0.49 && imageAlpha == 0) {
                        imageAlpha = 255
                        scaleX = -1f
                    }
                }

        revealAnim.withEndAction(onComplete)
        revealAnim.setDuration(500).start()
    }

    fun hide(lastView: FlipImageView, onComplete: () -> Unit) {

         val rotationA = rotationY // 180
         val rotationB = lastView.rotationY

         ValueAnimator.ofFloat(0f, 1f).apply {
             duration = 500
             addUpdateListener {

                 val a = it.animatedValue as Float

                 rotationY = rotationA - rotationA * a
                 lastView.rotationY = rotationB - rotationB * a

                 if (a > 0.49f && imageAlpha != 0) {
                     imageAlpha = 0
                     lastView.imageAlpha = 0
                     scaleX = 1f
                     lastView.scaleX = 1f
                 }
             }
             addListener(object : Animator.AnimatorListener {
                 override fun onAnimationStart(animation: Animator?) {
                 }

                 override fun onAnimationEnd(animation: Animator?) {
                     onComplete()
                 }

                 override fun onAnimationCancel(animation: Animator?) {
                 }

                 override fun onAnimationRepeat(animation: Animator?) {
                 }

             })
             startDelay = 500
             start()
         }
     }

    fun removeSelf() {
      ValueAnimator.ofFloat(1f, 0f).apply {
            addUpdateListener {
                alpha = (animatedValue as Float)
            }
            addListener(object :AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    val fParent = parent
                    if (fParent is ViewGroup) {
                        fParent.removeView(this@FlipImageView)
                    }
                }
            })
            duration = 250
            start()
        }
    }

}