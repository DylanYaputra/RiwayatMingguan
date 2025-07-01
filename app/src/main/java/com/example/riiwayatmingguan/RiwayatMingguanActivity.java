package com.example.riiwayatmingguan;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class RiwayatMingguanActivity extends AppCompatActivity {

    private BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_mingguan);

        // Inisialisasi Realm
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();

        // Inisialisasi bar chart
        barChart = findViewById(R.id.barChart);

        // Dapatkan data 7 hari terakhir dari Realm
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -6);
        Date tujuhHariLalu = calendar.getTime();

        RealmResults<AsupanHarian> hasil = realm.where(AsupanHarian.class)
                .greaterThanOrEqualTo("tanggal", tujuhHariLalu)
                .sort("tanggal", Sort.ASCENDING)
                .findAll();

        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < hasil.size(); i++) {
            AsupanHarian data = hasil.get(i);
            entries.add(new BarEntry(i, data.getTotalKalori()));
        }

        setupBarChart(entries);

    }

    private void isiDataDummy(Realm realm) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -6); // mulai dari 6 hari lalu

        int[] dummyKalori = {1500, 1700, 1600, 1800, 1750, 1900, 1650};

        realm.executeTransaction(r -> {
            for (int i = 0; i < 7; i++) {
                AsupanHarian data = r.createObject(AsupanHarian.class, java.util.UUID.randomUUID().toString());
                data.setTanggal(calendar.getTime());
                data.setTotalKalori(dummyKalori[i]);

                calendar.add(Calendar.DAY_OF_YEAR, 1); // maju ke hari berikutnya
            }
        });
    }




    private void setupBarChart(ArrayList<BarEntry> entries) {
        BarDataSet dataSet = new BarDataSet(entries, "Gizi");
        dataSet.setColor(0xFFB6C5A7); // warna grafik
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.9f);

        barChart.setData(data);
        barChart.setFitBars(true);
        barChart.getDescription().setEnabled(false);

        // Label hari
        String[] days = new String[]{"Mon", "Tue", "Wia", "Tilu", "Fir", "Sa", "Sun"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(days));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);

        barChart.invalidate(); // refresh chart
    }
}
