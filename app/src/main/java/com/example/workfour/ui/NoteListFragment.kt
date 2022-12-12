package com.example.workfour.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.workfour.Note
import com.example.workfour.NoteListAdapter
import com.example.workfour.R
import com.example.workfour.databinding.FragmentMainBinding
import com.example.workfour.room.NoteData
import kotlin.random.Random

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NoteListFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    private lateinit var viewModel: NoteViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity())[NoteViewModel::class.java]
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NoteListAdapter()
        adapter.itemClick {
            val bundle = Bundle()
            bundle.putInt("ID", it.id)
            Navigation.findNavController(binding.root).navigate(
                R.id.action_SecondFragment_to_FirstFragment,
                bundle
            )
        }
        viewModel.getAll()?.observe(requireActivity()) {

            if (it.isEmpty()) {
                val list = initNoteList()
                list.forEach { note ->
                    viewModel.insert(note)
                }
            }

            adapter.items = it
        }

        binding.noteRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initNoteList(): List<NoteData> {

        val result = arrayListOf<NoteData>()

        for (i in 0..100) {
            result.add(
                NoteData(
                    i,
                    "Name$i", Random.nextDouble(500.0),
                    "description$i",
                    "https://c.dns-shop.ru/thumb/st4/fit/0/0/49835446fb40ca294f7ae68566b59e34/0ecf45c4172a57b5d2636530987139a3aa78c6c215eca45a1195fe6d4fcdb57a.jpg.webp",
                    false
                )
            )
        }
        return result
    }

}