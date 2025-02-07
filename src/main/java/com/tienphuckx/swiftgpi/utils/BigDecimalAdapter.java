package com.tienphuckx.swiftgpi.utils;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.math.BigDecimal;

public class BigDecimalAdapter extends XmlAdapter<String, BigDecimal> {
    @Override
    public BigDecimal unmarshal(String v) {
        return new BigDecimal(v);
    }

    @Override
    public String marshal(BigDecimal v) {
        return v.toPlainString();
    }
}
