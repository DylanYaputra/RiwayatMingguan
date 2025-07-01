package com.example.riiwayatmingguan;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import java.util.Date;
import java.util.UUID;

public class AsupanHarian extends RealmObject {

    @PrimaryKey
    private String id;

    private Date tanggal;
    private int totalKalori;

    // --- Constructor kosong (wajib untuk Realm) ---
    public AsupanHarian() {
        this.id = UUID.randomUUID().toString(); // set ID otomatis
    }

    // --- Getter dan Setter ---

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public int getTotalKalori() {
        return totalKalori;
    }

    public void setTotalKalori(int totalKalori) {
        this.totalKalori = totalKalori;
    }
}
