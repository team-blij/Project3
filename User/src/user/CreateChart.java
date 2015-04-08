/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.jfree.chart.ChartFactory;
import java.util.Date;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import java.util.Calendar;
import javax.swing.ImageIcon;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author School
 */

//TimeSeries timeData = new TimeSeries("Time");
//timeData.add( new Day(22, 9, 2014), your number);
//... // add new value
//TimeSeriesCollection dataset = new TimeSeriesCollection(timeData);
//JFreeChart chart = ChartFactory.createTimeSeriesChart(chartTitle, xAxisLabel, yAxisLabel, dataset);
public class CreateChart {

//    public void mainChart()
//    {
//        CategoryDataset dataset = createDataset();
//        JFreeChart chart = createChart(dataset);
//        final ChartPanel chartPanel = new ChartPanel(chart);
//        chartPanel.setPreferredSize(new Dimension(500, 270));
//        add(chartPanel);
//
//    }
    private PieDataset createPieDataset() {
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Linux", 29);
        result.setValue("Mac", 20);
        result.setValue("Windows", 51);
        return result;

    }

    private JFreeChart createChart(final CategoryDataset dataset) {

        final JFreeChart chart3 = ChartFactory.createPieChart("title",createPieDataset(), true, true, true);

        final JFreeChart chart2 = ChartFactory.createXYLineChart("title", "y-as", "x-as", creatXYDataset(), PlotOrientation.HORIZONTAL, true, true, false);

        XYPlot plot2 = chart2.getXYPlot();
        chart2.setBackgroundPaint(Color.white);
        
        
        
        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
        plot2.setRenderer(renderer2);
        
        
        renderer2.setSeriesStroke(0, new BasicStroke(6.0f));
        renderer2.setSeriesStroke(1, new BasicStroke(6.0f));
        
        

        final JFreeChart chart = ChartFactory.createBarChart(
                "Poplulaire dieren", // chart title
                "Category", // domain axis label
                "Value", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips?
                false // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        // set up gradient paints for series...
        final GradientPaint gp0 = new GradientPaint(
                0.0f, 0.0f, Color.blue,
                0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp1 = new GradientPaint(
                0.0f, 0.0f, Color.green,
                0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp2 = new GradientPaint(
                0.0f, 0.0f, Color.red,
                0.0f, 0.0f, Color.lightGray
        );

        final GradientPaint gp3 = new GradientPaint(
                0.0f, 0.0f, Color.yellow,
                0.0f, 0.0f, Color.lightGray
        );

        final GradientPaint gp4 = new GradientPaint(
                0.0f, 0.0f, Color.magenta,
                0.0f, 0.0f, Color.lightGray
        );

        final GradientPaint gp5 = new GradientPaint(
                0.0f, 0.0f, Color.black,
                0.0f, 0.0f, Color.lightGray
        );

        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);
        renderer.setSeriesPaint(3, gp3);
        renderer.setSeriesPaint(4, gp4);
        renderer.setSeriesPaint(5, gp5);

        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        // OPTIONAL CUSTOMISATION COMPLETED.

        return chart3;

    }

    public CreateChart() {

        final CategoryDataset dataset = createAreaDataset();
        final JFreeChart chart = createChart(dataset);
        ImageIcon ii = new ImageIcon(chart.createBufferedImage(592, 500));

        NewJPanel2.setChartLabel(ii);
        System.out.println("TEST");

    }

    private CategoryDataset createAreaDataset() {

        // row keys...
        final String series1 = "Afrika";
        final String series2 = "Australie";
        final String series3 = "Europa";
        final String series4 = "Noord-Amerika";
        final String series5 = "Oceanium";
        final String series6 = "Zuid-Afrika";

        Calendar c = Calendar.getInstance();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println(dateFormat.format(c.getTime()));

        c.add(Calendar.DATE, -4);

        final String category1 = dateFormat.format(c.getTime());

        c.add(Calendar.DATE, 1);
        final String category2 = dateFormat.format(c.getTime());

        c.add(Calendar.DATE, 1);
        final String category3 = dateFormat.format(c.getTime());
        final String category4 = "Yesterday";
        final String category5 = "Today";

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(1.0, series1, category1);
        dataset.addValue(4.0, series1, category2);
        dataset.addValue(3.0, series1, category3);
        dataset.addValue(5.0, series1, category4);
        dataset.addValue(5.0, series1, category5);

        dataset.addValue(5.0, series2, category1);
        dataset.addValue(7.0, series2, category2);
        dataset.addValue(6.0, series2, category3);
        dataset.addValue(8.0, series2, category4);
        dataset.addValue(4.0, series2, category5);

        dataset.addValue(4.0, series3, category1);
        dataset.addValue(3.0, series3, category2);
        dataset.addValue(2.0, series3, category3);
        dataset.addValue(3.0, series3, category4);
        dataset.addValue(6.0, series3, category5);

        dataset.addValue(4.0, series4, category1);
        dataset.addValue(3.0, series4, category2);
        dataset.addValue(2.0, series4, category3);
        dataset.addValue(3.0, series4, category4);
        dataset.addValue(6.0, series4, category5);

        dataset.addValue(4.0, series5, category1);
        dataset.addValue(3.0, series5, category2);
        dataset.addValue(2.0, series5, category3);
        dataset.addValue(3.0, series5, category4);
        dataset.addValue(6.0, series5, category5);

        dataset.addValue(4.0, series6, category1);
        dataset.addValue(3.0, series6, category2);
        dataset.addValue(2.0, series6, category3);
        dataset.addValue(3.0, series6, category4);
        dataset.addValue(6.0, series6, category5);

        return dataset;

    }

    public XYDataset creatXYDataset() {
        XYSeries team1_xy_data = new XYSeries("Temperature", false);
        team1_xy_data.add(10, 1);
        team1_xy_data.add(12, 2);
        team1_xy_data.add(13, 3);
        team1_xy_data.add(9, 4);
        team1_xy_data.add(23, 5);

        XYSeries team2_xy_data = new XYSeries("Tweets", false);
        team2_xy_data.add(12, 1);
        team2_xy_data.add(12, 2);
        team2_xy_data.add(15, 3);
        team2_xy_data.add(2, 4);
        team2_xy_data.add(26, 5);

        /* Add all XYSeries to XYSeriesCollection */
        //XYSeriesCollection implements XYDataset
        XYSeriesCollection my_data_series = new XYSeriesCollection();
        // add series using addSeries method
        my_data_series.addSeries(team1_xy_data);
        my_data_series.addSeries(team2_xy_data);
                //my_data_series.addSeries(team3_xy_data);

        return my_data_series;
    }

}
