package com.example.planetZe_gp5;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.ArrayList;

// for charts
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.enums.LegendLayout;
import com.anychart.enums.Align;

import com.anychart.charts.Pie;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;

import com.anychart.APIlib;
import com.example.planetZe_gp5.acf.Calculation;
// for firebase
// import com.google.firebase.database.FirebaseDatabase;

// print to console
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EcoGaugeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EcoGaugeFragment extends Fragment implements Observer {
    DataModel dbModel;
    Button testButton;
    static String testVar;
    ArrayList<String> totalEmissionArray; // Total emission
    ArrayList<String> CAPCarray; // CountryAnnualPerCapita
    ArrayList<ValueDataEntry> emission_categories = new ArrayList<>();
    AnyChartView anyChartView_pie;
    Pie pie;
    Set set;  // for refreshing line chart

    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extractData();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_eco_gauge, container, false);
        return view;
    }

    public void extractData(){
        dbModel = DataModel.getInstance();
        dbModel.readValue("CountryAnnualPerCapita", this);

        // value provided in other classes:
        // A) Total carbon emission

        // B) Emission by category

        // C) Emission trend (?)
    }

    @Override
    public void updateAfterRead(Object valueRead) {
        CAPCarray = (ArrayList<String>) valueRead;
    }

    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value, Number value2, Number value3) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
        }

    }

    /*
      @params
      String horizontalLinePos: a String(yes) of a double representing the desired height
      of the horizontal line.
      An empty string indicates that no horizontal line should be plotted.
     */
    public void addLineChart(String horizontalLinePos, boolean isAnimated) {

        AnyChartView anyChartView_line = getView().findViewById(R.id.line_chart);
        APIlib.getInstance().setActiveAnyChartView(anyChartView_line);

        Cartesian cartesian = AnyChart.line();
        // cartesian = AnyChart.line();

        // cartesian.animation(isAnimated);
        cartesian.animation(true);

        cartesian.padding(10d, 20d, 5d, 20d);

        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .xLabel(true)
                .yLabel(true)
                // TODO ystroke
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.yAxis(0).title("Emission (per capita)");
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

        List<DataEntry> seriesData = new ArrayList<>();
        for (int i = 0; i < 24; ++i) {
            seriesData.add(new ValueDataEntry(String.valueOf(i + 1),Calculation.getInstance().totalCF));
        }


        // Set set = Set.instantiate();
        set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");

        // line: daily emission
        Line series1 = cartesian.line(series1Mapping);
        series1.name("You");
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);

        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);

        // horizontal line
        cartesian.annotations().horizontalLine("").valueAnchor(String.valueOf(Calculation.getInstance().totalCF));

        anyChartView_line.setChart(cartesian);
    }

    public void addPieChart() {
        // Credit: example code
        // https://github.com/AnyChart/AnyChart-Android/blob/master/sample/src/main/java/com/anychart/sample/charts/PieChartActivity.java

        // define pie chart
        anyChartView_pie = getView().findViewById(R.id.pie_chart);

        APIlib.getInstance().setActiveAnyChartView(anyChartView_pie);

        // anyChartView_pie.clear();
        pie = AnyChart.pie();

        // data to plot
        List<DataEntry> data = new ArrayList<>();

        // populate pie chart data
        emission_categories.add(new ValueDataEntry("Consumption", Calculation.getInstance().consumptionCF));
        emission_categories.add(new ValueDataEntry("Food", Calculation.getInstance().foodCF));
        emission_categories.add(new ValueDataEntry("Housing", Calculation.getInstance().housingCF));
        emission_categories.add(new ValueDataEntry("Transportation", Calculation.getInstance().transportCF));

        for (int i = 0; i < 4; ++i) {
            // TODO: add actual values
            data.add(emission_categories.get(i));
        }

        pie.data(data);

        // pie chart options
        pie.title("Emissions Breakdown by Category");

        pie.labels().position("outside");

        pie.legend().title().enabled(true);
        pie.legend().title()
                .text("Emission Categories")
                .padding(0d, 0d, 10d, 0d);

        pie.legend()
                .position("bottom")
                .itemsLayout(LegendLayout.HORIZONTAL)
                .align(Align.CENTER);

        // Not sure why it doesnt respond to the arguments accordingly
        // pie.animation(isAnimated);
        pie.animation(true);

        anyChartView_pie.setChart(pie);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // retrieves data from firebase and populate the respective observers
        extractData();

        addPieChart();
        addLineChart("", true);

        TextView ttl_emiss = getView().findViewById(R.id.total_emission);
        //ttl_emiss.setText("Total emission: " + String.valueOf());

        // testVar = String.valueOf(UsersACFarray.size());
        // testVar = String.valueOf(emission_categories.size());

        // dummy button for testing
        testButton = view.findViewById(R.id.testbutton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        // comparison selector
        Spinner comparison_categories = view.findViewById(R.id.compare_with_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.comparison_categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        comparison_categories.setAdapter(adapter);

        // set event listeners upon changing selection
        comparison_categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = "Total Emission: " + String.valueOf(
                        Math.round(Calculation.getInstance().totalCF * 100) / 100.0
                ) + " kg CO2e";
                ttl_emiss.setText(s);
                /*
                  + String.valueOf(UsersCategoryACFarray.get(0))
                  + String.valueOf(UsersCategoryACFarray.get(1))
                  + String.valueOf(UsersCategoryACFarray.get(2))
                  + String.valueOf(UsersCategoryACFarray.get(3));
                // t, C, F, H */
                /* + String.valueOf(UsersACFarray.get(0))
                + String.valueOf(UsersACFarray.get(1))
                + String.valueOf(UsersACFarray.get(2))
                + String.valueOf(UsersACFarray.get(3))
                + String.valueOf(UsersACFarray.get(4))
                + String.valueOf(UsersACFarray.get(5)); // 123212 (wrong order) */

                String selection = comparison_categories.getSelectedItem().toString();

                if (selection.equals("Global average")) {
                    /* anyChartView_pie.clear();
                    // define new pie chart
                    Pie pie = AnyChart.pie();

                    ArrayList<DataEntry> data = new ArrayList<>();
                    data.add(new ValueDataEntry("Apples", 6371664));
                    data.add(new ValueDataEntry("onItemSelected", 789622));

                    pie.data(data);


                    anyChartView_pie.setChart(pie); */


                    // Could not reload chart according to arguments
                    addPieChart();
                    addLineChart("2.0", false);
                } else if (selection.equals("National average")) {
                    addPieChart();
                    addLineChart("10.0", false);
                } else {
                    addPieChart();
                    addLineChart("", false);
                }

                ttl_emiss.setText(s);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // pie chart and line chart
        // needed, will be overwritten by method call in onItemSelected instantly
        // dt = new ArrayList<>();

        // updatePieChart();
        // anyChartView_pie.clear();
        addPieChart();
        addLineChart("5.0", true);
    }
}