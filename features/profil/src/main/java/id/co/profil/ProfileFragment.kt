package id.co.profil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.core.data.model.Quiz
import id.co.profil.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var dataBinding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HistoryAdapter()
        dataBinding.rvHistory.layoutManager = LinearLayoutManager(requireContext())
        dataBinding.rvHistory.adapter = adapter

        val lisQuiz = mutableListOf<Quiz>()
        lisQuiz.add(
            Quiz(
            "Quiz 1",
            "Math",
            "20 Soal",
                "120"
        )
        )
        lisQuiz.add(
            Quiz(
            "Quiz 2",
            "Math",
            "20 Soal",
                "120"
        )
        )
        lisQuiz.add(
            Quiz(
            "Quiz 3",
            "Math",
            "20 Soal",
                "90"
        )
        )

        adapter.setListHistory(lisQuiz)

    }
}