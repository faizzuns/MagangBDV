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
public class EventsFragment extends Fragment implements NewsEventListener {

    RecyclerView rvEvent;
    NewsEventAdapter adapter;
    ArrayList<DummyEvent> listEvent;


    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_events, container, false);

        rvEvent = (RecyclerView)v.findViewById(R.id.rv_event);
        listEvent = new ArrayList<>();

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        rvEvent.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rvEvent.setLayoutManager(llm);

        adapter = new NewsEventAdapter(listEvent,this);
        rvEvent.setAdapter(adapter);
        callEvent();

        super.onActivityCreated(savedInstanceState);
    }

    private void callEvent() {
        listEvent = new ArrayList<>();

        DummyEvent event;

        event = new DummyEvent("Kunjungan Universitas Indonesia",20,6,2017,20,6,2017,10,12,"Kunjungan dari Universitas Indonesia ke Bandung Digital Valley yang berisikan mahasiswa mahasiswa jurusan Sistem Informasi dan Lingkungan");
        listEvent.add(event);
        event = new DummyEvent(R.drawable.tes_event_1,"BNI HackFest",23,6,2017,24,6,2017,8,20,"BNI HACKFEST merupakan kompetisi pengembangan aplikasi secara marathon 1 x 24 jam. Ajang ini digelar di lima kota, yakni Malang, Makassar, Jogjakarta, Bandung, dan Medan. BNI HACKFEST mengundang talenta-talenta yang tergabung dalam tim terbaik guna mempersembahkan karyanya dalam bentuk model bisnis hingga prototipe aplikasi. Dengan seleksi ketat, tim yang berkompetisi diharapkan akan lebih matang dari aspek penggunaan dan manfaatnya.");
        listEvent.add(event);
        event = new DummyEvent("Kunjungan Universitas Brawijaya",22,6,2017,22,6,2017,10,12,"Kunjungan dari Universitas Brawijaya ke Bandung Digital Valley yang berisikan mahasiswa mahasiswa jurusan Bisnis dan Manajemen");
        listEvent.add(event);


        adapter.refreshData(listEvent);
    }

    @Override
    public void onCardClicked(DummyEvent dummyEvent) {

    }
}
