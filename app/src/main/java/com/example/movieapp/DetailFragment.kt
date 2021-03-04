package com.example.movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_add_note.view.*
import kotlinx.android.synthetic.main.fragment_serie_detail.view.*

class DetailFragment() : Fragment() {

    private var title: String=""
    private var subTitle: String=""
    private var dateTime: String=""
    private var url: String=""
    private var description: String=""
    private var imageNote: String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title= arguments?.getString("Title").toString()
        subTitle= arguments?.getString("Subttitle").toString()
        dateTime= arguments?.getString("DateTime").toString()
        url= arguments?.getString("Url").toString()
        description= arguments?.getString("Description").toString()
        imageNote= arguments?.getString("imgNote").toString()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_serie_detail, container, false)

        rootView.etNoteTitleLands.setText(title)
        rootView.etNoteSubTitle.setText(subTitle)
        rootView.tvDateTimeLands.text = dateTime
        rootView.etWebLinkLands.setText(url)
        rootView.etNoteDescLands.setText(description)
        //rootView.imgNoteFrg.text =imageNote

        return rootView
    }

}
