package br.com.questv.util.chart;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

public class DecimalDataFormatter implements IValueFormatter {

  private DecimalFormat mFormat;

  public DecimalDataFormatter() {
    mFormat = new DecimalFormat("###,###,##0"); // use one decimal
  }

  @Override
  public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
    // write your logic here
    return mFormat.format(value); // e.g. append a dollar-sign
  }
}
