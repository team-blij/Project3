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

/**
 *
 * @author School
 */
public class CreateChart{
    
     public CreateChart(ResultSet result, String name) {
              
      ImageIcon ii = new ImageIcon(chart.createBufferedImage(592, 500));    
          
      NewJPanel2.setChartLabel(ii);   
           
    }//end of creatChart()
     
private ImageIcon PieChart(ResultSet resultSet){

      JFreeChart chart = ChartFactory.createPieChart(
         "Mobile Sales",  // chart title           
         dataset,         // data           
         true,            // include legend          
         true,           
         false );
      
         ImageIcon ii = new ImageIcon(chart.createBufferedImage(592, 500)); 
      return ii;
}

  
}//end of class CreateChart
