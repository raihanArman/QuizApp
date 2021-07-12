package id.co.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.core.data.model.Quiz
import id.co.search.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    private lateinit var dataBinding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentSearchBinding.inflate(inflater)
        return dataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SearchAdapter()
        dataBinding.rvQuiz.layoutManager = LinearLayoutManager(requireContext())
        dataBinding.rvQuiz.adapter = adapter

        val lisQuiz = mutableListOf<Quiz>()
        lisQuiz.add(Quiz(
            "Quiz 1",
            "Math",
            "20 Soal",
        ))
        lisQuiz.add(Quiz(
            "Quiz 2",
            "Math",
            "20 Soal",
        ))
        lisQuiz.add(Quiz(
            "Quiz 3",
            "Math",
            "20 Soal",
        ))

        adapter.setQuizList(lisQuiz)

    }
}