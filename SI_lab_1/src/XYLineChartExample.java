import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;

public class XYLineChartExample extends JFrame {
    private XYDataset dataset;
    private XYPlot plot;
    private double axiseYMax;
    private double axiseYMin;
    public XYLineChartExample(XYDataset dataset, double axiseYMax, double axiseYMin) {
        super("XY Line Chart Example with JFreechart");
         this.dataset = dataset;
         this.axiseYMax = axiseYMax;
         this.axiseYMin = axiseYMin;
        JPanel chartPanel = createChartPanel();
        add(chartPanel, BorderLayout.CENTER);

        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JPanel createChartPanel() {
        String chartTitle = "TTP";
        String xAxisLabel = "X";
        String yAxisLabel = "Y";
        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset, PlotOrientation.VERTICAL,true,false,false);
        this.plot = chart.getXYPlot();
        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setTickUnit(new NumberTickUnit(5));

        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setRange(axiseYMin, axiseYMax);
        //yAxis.setAutoRange(true);
        return new ChartPanel(chart);
    }
}