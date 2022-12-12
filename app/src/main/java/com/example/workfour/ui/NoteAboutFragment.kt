package com.example.workfour.ui
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.workfour.databinding.FragmentNoteAboutBinding
import com.squareup.picasso.Picasso


class NoteAboutFragment : Fragment() {

    lateinit var binding: FragmentNoteAboutBinding
    lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoteAboutBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[NoteViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getInt("ID")
        if (id != -1){
            id?.let {
                viewModel.getById(id)
            }
        }

        viewModel.selectedNote.observe(requireActivity()) {
            if (it != null){
                binding.note = it
                Picasso.get().load(it.img).into(binding.imageView4)
            }
        }

    }


}