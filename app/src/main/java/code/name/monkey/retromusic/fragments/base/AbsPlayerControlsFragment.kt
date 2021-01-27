/*
 * Copyright (c) 2020 Hemanth Savarla.
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 */
package code.name.monkey.retromusic.fragments.base

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.annotation.LayoutRes
import code.name.monkey.appthemehelper.util.ColorUtil
import code.name.monkey.appthemehelper.util.MaterialValueHelper
import code.name.monkey.appthemehelper.util.TintHelper
import code.name.monkey.retromusic.R
import code.name.monkey.retromusic.fragments.VolumeFragment
import code.name.monkey.retromusic.helper.MusicPlayerRemote
import code.name.monkey.retromusic.helper.MusicProgressViewUpdateHelper
import code.name.monkey.retromusic.helper.PlayPauseButtonOnClickHandler
import code.name.monkey.retromusic.util.PreferenceUtil
import code.name.monkey.retromusic.util.color.MediaNotificationProcessor
import kotlinx.android.synthetic.main.fragment_blur_player_playback_controls.playPauseButton
import kotlinx.android.synthetic.main.fragment_blur_player_playback_controls.nextButton
import kotlinx.android.synthetic.main.fragment_blur_player_playback_controls.previousButton

/**
 * Created by hemanths on 24/09/17.
 */

abstract class AbsPlayerControlsFragment(@LayoutRes layout: Int) : AbsMusicServiceFragment(layout),
    MusicProgressViewUpdateHelper.Callback {

    var lastPlaybackControlsColor: Int = 0

    protected abstract fun show()

    protected abstract fun hide()

    protected abstract fun updateShuffleState()

    //has to be implemented inside child classes because default uses fragment specific buttons
    protected abstract fun updateRepeatState()
//    fun updateRepeatState() {
//        when (MusicPlayerRemote.repeatMode) {
//            MusicService.REPEAT_MODE_NONE -> {
//                repeatButton.setImageResource(R.drawable.ic_repeat)
//                repeatButton.setColorFilter(
//                        lastDisabledPlaybackControlsColor,
//                        PorterDuff.Mode.SRC_IN
//                )
//            }
//            MusicService.REPEAT_MODE_ALL -> {
//                repeatButton.setImageResource(R.drawable.ic_repeat)
//                repeatButton.setColorFilter(lastPlaybackControlsColor, PorterDuff.Mode.SRC_IN)
//            }
//            MusicService.REPEAT_MODE_THIS -> {
//                repeatButton.setImageResource(R.drawable.ic_repeat_one)
//                repeatButton.setColorFilter(lastPlaybackControlsColor, PorterDuff.Mode.SRC_IN)
//            }
//        }
//    }

    protected abstract fun setUpProgressSlider()

    abstract fun setColor(color: MediaNotificationProcessor)

    abstract fun setUpRepeatButton() //has to be implemented inside child classes because default uses fragment specific buttons
    abstract fun setUpShuffleButton() //has to be implemented inside child classes because default uses fragment specific buttons
//    override fun setUpRepeatButton() {
//        repeatButton.setOnClickListener { MusicPlayerRemote.cycleRepeatMode() }
//    }
    fun setFabColor(i: Int) {
        TintHelper.setTintAuto(
                playPauseButton,
                MaterialValueHelper.getPrimaryTextColor(context, ColorUtil.isColorLight(i)),
                false
        )
        TintHelper.setTintAuto(playPauseButton, i, true)
    }

    protected fun setUpPlayPauseFab() {
        playPauseButton.setOnClickListener(PlayPauseButtonOnClickHandler())
    }

    fun updatePlayPauseDrawableState() {
        when {
            MusicPlayerRemote.isPlaying -> playPauseButton.setImageResource(R.drawable.ic_pause)
            else -> playPauseButton.setImageResource(R.drawable.ic_play_arrow)
        }
    }

    fun setUpMusicControllers() {
        setUpPlayPauseFab()
        setUpPrevNext()
        setUpRepeatButton()
        setUpShuffleButton()
        setUpProgressSlider()
    }

    fun setUpPrevNext() {
        updatePrevNextColor()
        nextButton.setOnClickListener { MusicPlayerRemote.playNextSong() }
        previousButton.setOnClickListener { MusicPlayerRemote.back() }
    }



    fun updatePrevNextColor() {
        nextButton!!.setColorFilter(lastPlaybackControlsColor, PorterDuff.Mode.SRC_IN)
        previousButton!!.setColorFilter(lastPlaybackControlsColor, PorterDuff.Mode.SRC_IN)
    }

    fun showBonceAnimation(view: View) {
        view.apply {
            clearAnimation()
            scaleX = 0.9f
            scaleY = 0.9f
            visibility = View.VISIBLE
            pivotX = (view.width / 2).toFloat()
            pivotY = (view.height / 2).toFloat()

            animate().setDuration(200)
                .setInterpolator(DecelerateInterpolator())
                .scaleX(1.1f)
                .scaleY(1.1f)
                .withEndAction {
                    animate().setDuration(200)
                        .setInterpolator(AccelerateInterpolator())
                        .scaleX(1f)
                        .scaleY(1f)
                        .alpha(1f)
                        .start()
                }
                .start()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideVolumeIfAvailable()
    }

    protected var volumeFragment: VolumeFragment? = null

    private fun hideVolumeIfAvailable() {
        if (PreferenceUtil.isVolumeVisibilityMode) {
            childFragmentManager.beginTransaction()
                .replace(R.id.volumeFragmentContainer, VolumeFragment()).commit()
            childFragmentManager.executePendingTransactions()
            volumeFragment =
                childFragmentManager.findFragmentById(R.id.volumeFragmentContainer) as VolumeFragment?
        }
    }

    companion object {
        const val SLIDER_ANIMATION_TIME: Long = 400
    }
}
