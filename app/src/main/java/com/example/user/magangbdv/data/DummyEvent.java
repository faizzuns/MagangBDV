package com.example.user.magangbdv.data;

import com.example.user.magangbdv.R;

/**
 * Created by User on 13/09/2017.
 */

public class DummyEvent {

    private int foto;
    private String judul;
    private int startDay;
    private int startMonth;
    private int startYear;
    private int finishDay;
    private int finishMonth;
    private int finishYear;
    private int hourMulai;
    private int hourSelesai;
    private String content;
    private int status;

    public DummyEvent(int foto, String judul, int startDay, int startMonth, int startYear, int finishDay, int finishMonth, int finishYear, int hourMulai, int hourSelesai, String content) {
        this.foto = foto;
        this.judul = judul;
        this.startDay = startDay;
        this.startMonth = startMonth;
        this.startYear = startYear;
        this.finishDay = finishDay;
        this.finishMonth = finishMonth;
        this.finishYear = finishYear;
        this.hourMulai = hourMulai;
        this.hourSelesai = hourSelesai;
        this.content = content;
        this.status = 1;
    }

    public DummyEvent(String judul, int startDay, int startMonth, int startYear, int finishDay, int finishMonth, int finishYear, int hourMulai, int hourSelesai, String content) {
        this.foto = R.drawable.bdv_logo;
        this.judul = judul;
        this.startDay = startDay;
        this.startMonth = startMonth;
        this.startYear = startYear;
        this.finishDay = finishDay;
        this.finishMonth = finishMonth;
        this.finishYear = finishYear;
        this.hourMulai = hourMulai;
        this.hourSelesai = hourSelesai;
        this.content = content;
        this.status = 1;
    }

    public DummyEvent(int foto, String judul, String content) {
        this.foto = foto;
        this.judul = judul;
        this.content = content;
        this.status = 0;
    }

    public int getStatus() {
        return status;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getFinishDay() {
        return finishDay;
    }

    public void setFinishDay(int finishDay) {
        this.finishDay = finishDay;
    }

    public int getFinishMonth() {
        return finishMonth;
    }

    public void setFinishMonth(int finishMonth) {
        this.finishMonth = finishMonth;
    }

    public int getFinishYear() {
        return finishYear;
    }

    public void setFinishYear(int finishYear) {
        this.finishYear = finishYear;
    }

    public int getHourMulai() {
        return hourMulai;
    }

    public void setHourMulai(int hourMulai) {
        this.hourMulai = hourMulai;
    }

    public int getHourSelesai() {
        return hourSelesai;
    }

    public void setHourSelesai(int hourSelesai) {
        this.hourSelesai = hourSelesai;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
