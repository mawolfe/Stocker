/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * ------------------------
 * CombinedXYPlotDemo1.java
 * ------------------------
 * (C) Copyright 2003, 2004, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited).
 * Contributor(s):   -;
 *
 * $Id $
 *
 * Changes
 * -------
 * 13-Jan-2003 : Version 1 (DG);
 *
 */

//package org.jfree.chart.demo;


import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.awt.Font;
import org.jfree.chart.annotations.XYTextAnnotation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * A demonstration application showing how to create a vertical combined chart.
 *
 */
public class drawChart extends ApplicationFrame {

	
	runPeriodicTask rpt;
    /**
     * Constructs a new demonstration application.
     *
     * @param title  the frame title.
     */
    public drawChart(final String tickerSymbol, String pathName, double lowThresh, double highThresh) {

        super("Stocker Chart");
        final JFreeChart chart = createCombinedChart(tickerSymbol, pathName, lowThresh, highThresh);
        final ChartPanel panel = new ChartPanel(chart, true, true, true, false, true);
        panel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(panel);
        
        this.pack();
        RefineryUtilities.centerFrameOnScreen(this);
        this.setVisible(true);

    }

    /**
     * Creates a combined chart.
     *
     * @return the combined chart.
     */
    private JFreeChart createCombinedChart(String tickerSymbol, String pathName, double lowThresh, double highThresh) {

        // create subplot 1...
        final XYDataset data1 = createDataset1(tickerSymbol,pathName,lowThresh,highThresh);
        final XYItemRenderer renderer1 = new StandardXYItemRenderer();
        final NumberAxis rangeAxis1 = new NumberAxis("Stock Price ($)");
        final XYPlot subplot1 = new XYPlot(data1, null, rangeAxis1, renderer1);
        subplot1.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        
//        final XYTextAnnotation annotation = new XYTextAnnotation("Hello!", 50.0, 10000.0);
//        annotation.setFont(new Font("SansSerif", Font.PLAIN, 9));
//        annotation.setRotationAngle(Math.PI / 4.0);
//        subplot1.addAnnotation(annotation);
        
        // create subplot 2...
//        final XYDataset data2 = createDataset2();
//        final XYItemRenderer renderer2 = new StandardXYItemRenderer();
//        final NumberAxis rangeAxis2 = new NumberAxis("Range 2");
//        rangeAxis2.setAutoRangeIncludesZero(false);
//        final XYPlot subplot2 = new XYPlot(data2, null, rangeAxis2, renderer2);
//        subplot2.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);

        // parent plot...
        final CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new NumberAxis("Time"));
        plot.setGap(10.0);
        
        // add the subplots...
        plot.add(subplot1, 1);
//        plot.add(subplot2, 1);
        plot.setOrientation(PlotOrientation.VERTICAL);

        // return a new chart containing the overlaid plot...
        return new JFreeChart(tickerSymbol,
                              JFreeChart.DEFAULT_TITLE_FONT, plot, true);
    }

    /**
     * Creates a sample dataset.
     *
     * @return Series 1.
     */
    private XYDataset createDataset1(String tickerSymbol, String pathName, double lowThresh, double highThresh) {

        // create dataset 1...
        final XYSeries series1 = new XYSeries(tickerSymbol);
        
        int entryNum = 1;
    	try {

    		BufferedReader input =  new BufferedReader(new FileReader(pathName + tickerSymbol + ".txt"));
    		
    		try {
	            String line = null; //not declared within while loop
	            
	            // readLine is a bit quirky :
	            // it returns the content of a line MINUS the newline.
	            // it returns null only for the END of the stream.
	            // it returns an empty String if two newlines appear in a row.
	            
	            System.out.println("* ------ START INPUT ------- *");
	            while (( line = input.readLine()) != null) {
	                System.out.println(line);
//	                sLL.addStock(line);
	                series1.add(entryNum, Double.parseDouble(line));
	                entryNum++;
	            }
    		} catch (IOException ioe) {
              ioe.printStackTrace();             
    		} finally {
    			System.out.println("* ------  END INPUT  ------- *\n\n");
    		}
    	} catch (FileNotFoundException fnfe ) {
    		fnfe.printStackTrace();
        }

System.out.println("i = " + entryNum);

        final XYSeries series2 = new XYSeries("Low Threshold");
        series2.add(1, lowThresh);
        series2.add(entryNum, lowThresh);

        final XYSeries series3 = new XYSeries("High Threshold");
        series3.add(1, highThresh);
        series3.add(entryNum, highThresh);

        final XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series1);
        collection.addSeries(series2);
        collection.addSeries(series3);
        return collection;

    }
    /* ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
 */
    
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
//    public static void main(final String[] args) {

//        final myXY2 demo = new myXY2("MENT", 4, 15);
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
//        demo.setVisible(true);

//    }
}