package com.example.news_eat_fronted.presentation.ui.mypage

import android.R.attr.thumbOffset
import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.example.news_eat_fronted.R
import com.example.news_eat_fronted.databinding.FragmentSetTtsBinding
import com.example.news_eat_fronted.util.base.BindingFragment
import java.util.Locale
import kotlin.getValue

class SetTTSFragment : BindingFragment<FragmentSetTtsBinding>(R.layout.fragment_set_tts) {

    private val modifyViewModel by activityViewModels<ModifyViewModel>()

    val speedMin = 0.6f
    val speedMax = 1.7f
    val speedStep = 0.1f

    val pitchMin = 0.6f
    val pitchMax = 1.2f
    val pitchStep = 0.1f

    private lateinit var tts: TextToSpeech


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        getTTSPrefs()
        setTTS()
        initTTS()
        addListeners()
//        addMarkers()
    }

    override fun onDestroy() {
        super.onDestroy()

        tts.stop()
        tts.shutdown()
    }

    private fun getTTSPrefs() {
        val prefs = requireContext().getSharedPreferences("TTS_PREFS", Context.MODE_PRIVATE)
        val savedSpeed = prefs.getFloat("TTS_SPEED", 1.0f)
        val savedPitch = prefs.getFloat("TTS_PITCH", 1.0f)

        modifyViewModel.setCurrentSpeed(savedSpeed)
        modifyViewModel.setCurrentPitch(savedPitch)
        modifyViewModel.setForceEnableNextBtn()
    }

    private fun setTTS() {
        // Speed
        binding.ttsSpeedSeekbar.max = 100
        binding.ttsSpeedSeekbar.progress = ((modifyViewModel.currentSpeed.value - speedMin) / (speedMax - speedMin) * 100).toInt()

        binding.ttsSpeedSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                if(fromUser) {
                    var newValue = speedMin + (progress / 100f) * (speedMax - speedMin)
                    newValue = (kotlin.math.floor(newValue / speedStep) * speedStep * 10).toInt() / 10f

                    modifyViewModel.setCurrentSpeed(newValue)

                    // Thumb도 step 단위로 이동
                    val newProgress = ((newValue - speedMin) / (speedMax - speedMin) * 100).toInt()
                    seekBar?.progress = newProgress

                    Log.d("ttsSpeedSeekbar", newValue.toString())
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })


        // Pitch
        binding.ttsPitchSeekbar.max = 100
        binding.ttsPitchSeekbar.progress = ((modifyViewModel.currentPitch.value - pitchMin) / (pitchMax - pitchMin) * 100).toInt()

        binding.ttsPitchSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                if(fromUser) {
                    var newValue = pitchMin + (progress / 100f) * (pitchMax - pitchMin)
                    newValue = (kotlin.math.floor(newValue / pitchStep) * pitchStep * 10).toInt() / 10f

                    modifyViewModel.setCurrentPitch(newValue)

                    val newProgress = ((newValue - pitchMin) / (pitchMax - pitchMin) * 100).toInt()
                    seekBar?.progress = newProgress

                    Log.d("ttsPitchSeekbar", newValue.toString())
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun initTTS() {
        tts = TextToSpeech(requireContext()) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts.language = Locale.KOREAN
                tts.setPitch(modifyViewModel.currentPitch.value)
                tts.setSpeechRate(modifyViewModel.currentSpeed.value)
            }
        }
    }

    private fun addListeners() {
        binding.btnTtsPlay.setOnClickListener {
            tts.setPitch(modifyViewModel.currentPitch.value)
            tts.setSpeechRate(modifyViewModel.currentSpeed.value)

            val text = "${getString(R.string.news_eat_slogan2)}. ${getString(R.string.news_eat_name)}"
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "TTS_TEST")
        }

        binding.btnTtsReset.setOnClickListener {
            val defaultSpeed = 1.0f
            val defaultPitch = 1.0f

            modifyViewModel.setCurrentSpeed(defaultSpeed)
            modifyViewModel.setCurrentPitch(defaultPitch)

            binding.ttsSpeedSeekbar.progress = ((defaultSpeed - speedMin) / (speedMax - speedMin) * 100).toInt()
            binding.ttsPitchSeekbar.progress = ((defaultPitch - pitchMin) / (pitchMax - pitchMin) * 100).toInt()
        }
    }

    private fun addMarkers() {
        // 적용할지 말지 고민중
        val min = 0.6f
        val max = 1.7f
        val markerValues =
            listOf(0.6f, 0.7f, 0.8f, 0.9f, 1.0f, 1.1f, 1.2f, 1.3f, 1.4f, 1.5f, 1.6f, 1.7f)

        binding.speedSeekbarContainer.post {
            val seekWidth =
                binding.ttsSpeedSeekbar.width - binding.ttsSpeedSeekbar.paddingLeft - binding.ttsSpeedSeekbar.paddingRight

            markerValues.forEach { value ->
                val ratio = (value - min) / (max - min)
                val positionX =
                    binding.ttsSpeedSeekbar.paddingLeft + ratio * seekWidth - thumbOffset

                val marker = View(requireContext()).apply {
                    setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.Gray300))
                    layoutParams = FrameLayout.LayoutParams(4, 20).apply {
                        topMargin = 50 // 원하는 만큼 아래로
                    }
                }

                marker.translationX = positionX
                binding.speedSeekbarContainer.addView(marker)
            }
        }
    }
}