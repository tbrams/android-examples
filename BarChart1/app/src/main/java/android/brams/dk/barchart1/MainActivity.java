package android.brams.dk.barchart1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BarChart chart = (BarChart) findViewById(R.id.chart);
        BarData data = new BarData(getXAxisValues(), getDataSet());

        chart.setData(data);
        chart.setDescription("My Chart");
        chart.animateXY(2000, 2000);
        chart.invalidate();

    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        valueSet1.add(new BarEntry(110.000f, 0));
        valueSet1.add(new BarEntry(40.000f, 1));
        valueSet1.add(new BarEntry(60.000f, 2));
        valueSet1.add(new BarEntry(30.000f, 3));
        valueSet1.add(new BarEntry(90.000f, 4));
        valueSet1.add(new BarEntry(100.000f, 5));

        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        valueSet2.add(new BarEntry(150.000f, 0));
        valueSet2.add(new BarEntry(90.000f, 1));
        valueSet2.add(new BarEntry(120.000f, 2));
        valueSet2.add(new BarEntry(60.000f, 3));
        valueSet2.add(new BarEntry(20.000f, 4));
        valueSet2.add(new BarEntry(80.000f, 5));

        // We need to set some colors for the default scheme of having cyan for both does not work well
        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Brand 1");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Brand 2");
        barDataSet2.setColor(Color.rgb(0, 0, 155));

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("JAN");
        xAxis.add("FEB");
        xAxis.add("MAR");
        xAxis.add("APR");
        xAxis.add("MAY");
        xAxis.add("JUN");
        return xAxis;
    }
}