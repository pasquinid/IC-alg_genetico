package br.edu.ufu.bcc.ic.view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

;

public class resultadosView {

    public void executar(ArrayList<Double> aptidoes,ArrayList<Double> geracoes) {
        OutputStream out = null;
        XYSeries paresXY = new XYSeries("execucao");

        double geracao = geracoes.get(0);
        double aptidao = aptidoes.get(0);
        paresXY.add( geracao , aptidao );

        for(int i=1; i<aptidoes.size();i++){
            geracao = geracoes.get(i);
            aptidao = aptidoes.get(i);
            paresXY.add( geracao , aptidao );
        }

        XYSeriesCollection data = new XYSeriesCollection(paresXY);
        JFreeChart chart = ChartFactory.createXYLineChart("Resultados obtidos","geracao","aptidao",
                data,PlotOrientation.VERTICAL,true,true,false);
        try {
            out = new FileOutputStream(new File("chart.png"));
            ChartUtilities.writeChartAsPNG(out, chart, 1920, 1080);
        } catch (IOException e) {
            System.out.println("ERRO : resultadosView -> executar()");
        }

    }

}
