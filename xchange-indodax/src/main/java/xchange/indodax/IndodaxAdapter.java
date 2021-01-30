package org.knowm.xchange.indodax;

import org.knowm.xchange.currency.CurrencyPair;

public class IndodaxAdapter {
  public static String toSymbol(CurrencyPair pair) {
    return pair.base.getCurrencyCode().toLowerCase()
        + "_"
        + pair.counter.getCurrencyCode().toLowerCase();
  }
}
