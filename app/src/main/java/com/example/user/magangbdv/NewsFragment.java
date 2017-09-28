package com.example.user.magangbdv;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.magangbdv.data.DummyEvent;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements NewsEventListener {

    RecyclerView rvNews;
    NewsEventAdapter adapter;
    ArrayList<DummyEvent> listNews;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news, container, false);

        rvNews = (RecyclerView)v.findViewById(R.id.rv_news);
        listNews = new ArrayList<>();

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        rvNews.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rvNews.setLayoutManager(llm);

        adapter = new NewsEventAdapter(listNews,this);
        rvNews.setAdapter(adapter);
        callNews();

        super.onActivityCreated(savedInstanceState);
    }

    private void callNews() {
        listNews = new ArrayList<>();

        DummyEvent event = new DummyEvent(R.drawable.bdv_logo,"Pontensi Startup digital Indonesia","Startup digital di Indonesia akan berkembang sangat pesat pada tahun 2020, ini semua dikarenakan indonesia sedang memajukan di bidang startup digital");
        listNews.add(event);
        event = new DummyEvent(R.drawable.bdv_logo,"Indonesia peringkat 3 jumlah startup","Indonesia menduduki peringkat ke tiga jumlah startup. indonesia masih dibawah India dan juga Amerika serikat yang memiliki sekitar 30.000 startup");
        listNews.add(event);

        adapter.refreshData(listNews);
    }

    @Override
    public void onCardClicked(DummyEvent dummyEvent) {

    }
}
