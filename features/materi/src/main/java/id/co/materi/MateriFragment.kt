package id.co.materi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import id.co.core.data.model.Materi
import id.co.materi.databinding.FragmentMateriBinding

class MateriFragment : Fragment() {

    private lateinit var dataBinding: FragmentMateriBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_materi, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MateriAdapter()
        dataBinding.rvMateri.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        dataBinding.rvMateri.adapter = adapter

        val listMateri = mutableListOf<Materi>()
        listMateri.add(Materi(
            "Mantap",
            "https://cdn1-production-images-kly.akamaized.net/FPC3LE3Klfhtx5UDUHt6RFNEgJE=/0x47:720x453/673x379/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/3499629/original/008637600_1625230400-Manchester_United_-_Jadon_Sancho.jpg"
        ))

        listMateri.add(Materi(
            "Mantap",
            "https://cdn1-production-images-kly.akamaized.net/FPC3LE3Klfhtx5UDUHt6RFNEgJE=/0x47:720x453/673x379/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/3499629/original/008637600_1625230400-Manchester_United_-_Jadon_Sancho.jpg"
        ))
        listMateri.add(Materi(
            "Mantap",
            "https://akcdn.detik.net.id/community/media/visual/2021/05/19/manchester-united.jpeg?w=700&q=90"
        ))
        listMateri.add(Materi(
            "Mantap",
            "https://cdn1-production-images-kly.akamaized.net/FPC3LE3Klfhtx5UDUHt6RFNEgJE=/0x47:720x453/673x379/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/3499629/original/008637600_1625230400-Manchester_United_-_Jadon_Sancho.jpg"
        ))
        listMateri.add(Materi(
            "Mantap",
            "https://akcdn.detik.net.id/community/media/visual/2021/05/19/manchester-united.jpeg?w=700&q=90"
        ))
        listMateri.add(Materi(
            "Mantap",
            "https://cdn1-production-images-kly.akamaized.net/FPC3LE3Klfhtx5UDUHt6RFNEgJE=/0x47:720x453/673x379/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/3499629/original/008637600_1625230400-Manchester_United_-_Jadon_Sancho.jpg"
        ))

        adapter.setListMateri(listMateri)

    }
}