package com.lcwaikiki.wms.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lcwaikiki.wms.databinding.FragmentHomeBinding
import android.media.MediaPlayer
import android.widget.Button
import com.lcwaikiki.wms.R
import androidx.appcompat.app.AppCompatActivity


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mediaPlayer1: MediaPlayer

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val play_button: Button = root.findViewById(R.id.voice_button)
        val stop_button: Button = root.findViewById(R.id.stop_button)
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.approved) // MediaPlayer'ı oluşturun
        mediaPlayer1 = MediaPlayer.create(requireContext(), R.raw.denied) // MediaPlayer'ı oluşturun

        play_button.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start() // Sesi çalmaya başlatın
            }
        }
        stop_button.setOnClickListener {
            if (!mediaPlayer1.isPlaying) {
                mediaPlayer1.start() // Sesi çalmaya başlatın
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer.release()
        mediaPlayer1.release()
        _binding = null
    }
}