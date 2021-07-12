package id.co.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.core.data.model.Category
import id.co.core.data.model.Materi
import id.co.home.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var dataBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val popularAdapter = PopularAdapter()
        val categoryAdapter = CategoryAdapter()

        dataBinding.rvPopular.apply {
            val horiz = LinearLayoutManager(requireContext())
            horiz.orientation = LinearLayoutManager.HORIZONTAL
            layoutManager = horiz
            adapter = popularAdapter
        }


        dataBinding.rvCategory.apply {
            val horiz = GridLayoutManager(requireContext(), 2)
            layoutManager = horiz
            adapter = categoryAdapter
        }

        val listMateri = mutableListOf<Materi>()
        listMateri.add(Materi(
            "C++ Programming",
            "https://image.shutterstock.com/image-vector/logo-c-programming-language-icon-600w-693173473.jpg",
            "20 Siswa"
        ))
        listMateri.add(Materi(
            "C++ Programming",
            "https://image.shutterstock.com/image-vector/logo-c-programming-language-icon-600w-693173473.jpg",
            "20 Siswa"
        ))
        listMateri.add(Materi(
            "C++ Programming",
            "https://image.shutterstock.com/image-vector/logo-c-programming-language-icon-600w-693173473.jpg",
            "20 Siswa"
        ))
        listMateri.add(Materi(
            "C++ Programming",
            "https://image.shutterstock.com/image-vector/logo-c-programming-language-icon-600w-693173473.jpg",
            "20 Siswa"
        ))

        val listCategory = mutableListOf<Category>()
        listCategory.add(Category(
            "Math",
            "https://akcdn.detik.net.id/community/media/visual/2021/05/19/manchester-united.jpeg?w=700&q=90",
        ))

        listCategory.add(Category(
            "Math",
            "https://akcdn.detik.net.id/community/media/visual/2021/05/19/manchester-united.jpeg?w=700&q=90",
        ))
        listCategory.add(Category(
            "Math",
            "https://akcdn.detik.net.id/community/media/visual/2021/05/19/manchester-united.jpeg?w=700&q=90",
        ))
        listCategory.add(Category(
            "Math",
            "https://akcdn.detik.net.id/community/media/visual/2021/05/19/manchester-united.jpeg?w=700&q=90",
        ))


        popularAdapter.setListMateri(listMateri)
        categoryAdapter.setListCategory(listCategory)

    }
}