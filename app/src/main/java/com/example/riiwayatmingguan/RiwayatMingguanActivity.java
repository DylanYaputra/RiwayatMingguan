package com.example.riiwayatmingguan;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.riiwayatmingguan.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class RiwayatMingguanActivity extends AppCompatActivity {

    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_mingguan);

        barChart = findViewById(R.id.barChart);
        setupBarChart();
    }

    private void setupBarChart() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 30));
        entries.add(new BarEntry(1, 55));
        entries.add(new BarEntry(2, 25));
        entries.add(new BarEntry(3, 35));
        entries.add(new BarEntry(4, 50));
        entries.add(new BarEntry(5, 70));
        entries.add(new BarEntry(6, 45));

        BarDataSet dataSet = new BarDataSet(entries, "Gizi");
        dataSet.setColor(0xFFB6C5A7); // warna seperti di gambar
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.9f);

        barChart.setData(data);
        barChart.setFitBars(true);
        barChart.getDescription().setEnabled(false);

        // Set X-axis labels
        String[] days = new String[]{"Mon", "Tue", "Wia", "Tilu", "Fir", "Sa", "Sun"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(days));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);

        barChart.invalidate(); // refresh
    }
}
