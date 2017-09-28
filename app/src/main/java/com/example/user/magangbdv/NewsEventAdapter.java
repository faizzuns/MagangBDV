package com.example.user.magangbdv;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.magangbdv.data.DummyEvent;

import java.util.ArrayList;

/**
 * Created by User on 13/09/2017.
 */

public class NewsEventAdapter extends RecyclerView.Adapter<NewsEventAdapter.ViewHolder> {

    ArrayList<DummyEvent> listEvent;
    NewsEventListener listener;

    public NewsEventAdapter(ArrayList<DummyEvent> listEvent, NewsEventListener listener) {
        this.listEvent = listEvent;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgCard;
        TextView txtJudul,txtDetailWaktu,txtContent;
        CardView card;

        public ViewHolder(View v){
            super(v);
            imgCard = (ImageView)v.findViewById(R.id.card_foto);
            txtJudul =(TextView)v.findViewById(R.id.card_judul);
            txtDetailWaktu = (TextView)v.findViewById(R.id.card_detail_waktu);
            txtContent = (TextView)v.findViewById(R.id.card_content);
            card = (CardView)v.findViewById(R.id.card_news_event);
        }
    }

    @Override
    public NewsEventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_news_event,parent,false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(NewsEventAdapter.ViewHolder holder, int position) {
        final DummyEvent event = listEvent.get(position);

        holder.imgCard.setImageResource(event.getFoto());
        holder.txtJudul.setText(event.getJudul());

        if (event.getStatus()==1){
            //set detail waktu
            holder.txtDetailWaktu.setVisibility(View.VISIBLE);
            String detail = setDetailWaktu(event);
            holder.txtDetailWaktu.setText(detail);
        }else{
            holder.txtDetailWaktu.setVisibility(View.GONE);
        }


        //set content
        String content = setContent(event.getContent());
        holder.txtContent.setText(content);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCardClicked(event);
            }
        });
    }

    private String setContent(String content) {

        String edtItem = content;

        if (edtItem.length()>100){
            edtItem = edtItem.substring(0,98)+"...";
        }

        return edtItem;
    }

    private String setDetailWaktu(DummyEvent event) {
        String edtItem;

        if ((event.getStartDay()==event.getFinishDay()) && (event.getStartMonth()==event.getFinishMonth()) && (event.getStartYear()==event.getFinishYear())){
            String thisMonth = getMonth(event.getStartMonth());
            edtItem = thisMonth + " " + event.getStartDay() + " @ "+setHour(event.getHourMulai())+" - "+setHour(event.getHourSelesai());
        }else{
            String start = getMonth(event.getStartMonth());
            String finish = getMonth(event.getFinishMonth());

            edtItem = start + " "+event.getStartDay()+" - "+finish+" "+event.getFinishDay()+" @ "+setHour(event.getHourMulai())+" - "+setHour(event.getHourSelesai());
        }

        return edtItem;

    }

    private String setHour(int hourSelesai) {
        String edtItem;

        if (hourSelesai<10){
            edtItem = "0"+hourSelesai+":00";
        }else{
            edtItem = hourSelesai+":00";
        }

        return edtItem;
    }

    private String getMonth(int startMonth) {
        String month;

        switch (startMonth){
            case 0:
                month = "Januari";
                break;
            case 1:
                month = "Februari";
                break;
            case 2:
                month = "Maret";
                break;
            case 3:
                month = "April";
                break;
            case 4:
                month = "Mei";
                break;
            case 5:
                month = "Juni";
                break;
            case 6:
                month = "Juli";
                break;
            case 7:
                month = "Agustus";
                break;
            case 8:
                month = "September";
                break;
            case 9:
                month = "Oktober";
                break;
            case 10:
                month ="November";
                break;
            case 11:
                month ="Desember";
                break;
            default:
                month = "";
                break;
        }

        return month;
    }

    @Override
    public int getItemCount() {
        return listEvent.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public void refreshData(ArrayList<DummyEvent> listEvent){
        this.listEvent = listEvent;
        notifyDataSetChanged();
    }
}
