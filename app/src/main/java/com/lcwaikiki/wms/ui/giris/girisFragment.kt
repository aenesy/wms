package com.lcwaikiki.wms.ui.giris

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lcwaikiki.wms.databinding.FragmentGirisBinding

class girisFragment : Fragment() {

    private var _binding: FragmentGirisBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val girisViewModel =
            ViewModelProvider(this).get(girisViewModel::class.java)

        _binding = FragmentGirisBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}