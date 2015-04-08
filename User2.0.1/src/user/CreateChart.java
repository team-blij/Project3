/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javafx.scene.chart.CategoryAxis;
import javax.swing.ImageIcon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Elize
 */
public class CreateChart{
    
        public CreateChart(DefaultPieDataset dataSet, String name) {
        NewJPanel2.setChartLabel(PieChart(dataSet, name));
        }//end of creatChart()
     
        public CreateChart(XYSeriesCollection dataSet, String name, String X, String Y) {
        NewJPanel2.setChartLabel(XYChart(dataSet, name, X, Y));
        }//end of creatChart()
        
        public CreateChart(CategoryDataset dataSet, String name, String X, String Y) {
        NewJPanel2.setChartLabel(barChart(dataSet, name, X, Y));
        }//end of creatChart()
     
       
private ImageIcon PieChart(DefaultPieDataset dataset, String name){

      JFreeChart chart = ChartFactory.createPieChart(
         name,              // chart title           
         dataset,           // data           
         true,              // include legend          
         true,           
         false );
      
         ImageIcon ii = new ImageIcon(chart.createBufferedImage(592, 500)); 
      return ii;
}//end of PieChart()

    private ImageIcon XYChart(XYSeriesCollection dataset, String name, String X, String Y){

      JFreeChart chart = ChartFactory.createXYLineChart(
            name,
            X, 
            Y, 
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );

         ImageIcon ii = new ImageIcon(chart.createBufferedImage(592, 500)); 
      return ii;
}//end of XYChart
    
    private ImageIcon barChart(CategoryDataset dataset, String name, String X, String Y){

      final JFreeChart chart = ChartFactory.createBarChart(
            name,                       // chart title
            X,                          // domain axis label
            Y,                          // range axis label
            dataset,                     // data
            PlotOrientation.VERTICAL,   // orientation
            true,                        // include legend
            true,                        // tooltips?
            false                         // URLs?
        );
      
         ImageIcon ii = new ImageIcon(chart.createBufferedImage(592, 500)); 
      return ii;
}//end of barChart()

  
}//end of class CreateChart
