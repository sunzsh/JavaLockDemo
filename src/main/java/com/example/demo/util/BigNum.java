package com.example.demo.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.function.BiFunction;

public class BigNum {

    private BigDecimal value;
    private synchronized BigDecimal convertBigDecimal(Object value, Integer scale) {
        if (value == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal res = null;
        if (value instanceof Number) {
            res = BigDecimal.valueOf(((Number) value).doubleValue());
        } else {
            try {
                res = new BigDecimal(value.toString());
            } catch (NumberFormatException e) {
                return BigDecimal.ZERO;
            }
        }

        if (scale != null) {
            res = BigDecimal.valueOf(res.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        return res;
    }

    public BigNum(Object value) {
        this.value = convertBigDecimal(value, null);
    }

    public static BigNum startOf(Object value) {
        return new BigNum(value);
    }

    public BigNum operator(BiFunction<BigDecimal, BigDecimal, BigDecimal> operator, Object other) {
        return operator(operator, other, null);
    }
    public BigNum operator(BiFunction<BigDecimal, BigDecimal, BigDecimal> operator, Object other, Integer beforeOperteScale) {
        if (other == null) {
            return this;
        }
        if (other instanceof BigNum) {
            this.value = operator.apply(this.value, ((BigNum) other).getValue());
            return this;
        }
        this.value = operator.apply(this.value, convertBigDecimal(other, beforeOperteScale));
        return this;
    }

    public BigNum add(Object other) {
        return operator(BigDecimal::add, other);
    }
    public BigNum add(Object other, Integer beforeOperteScale) {
        return operator(BigDecimal::add, other, beforeOperteScale);
    }

    public BigNum subtract(Object other) {
        return operator(BigDecimal::subtract, other);
    }
    public BigNum subtract(Object other, Integer beforeOperteScale) {
        return operator(BigDecimal::subtract, other, beforeOperteScale);
    }

    public BigNum multiply(Object other) {
        return operator(BigDecimal::multiply, other);
    }
    public BigNum multiply(Object other, Integer beforeOperteScale) {
        return operator(BigDecimal::multiply, other, beforeOperteScale);
    }

    public BigNum divide(Object other) {
        return operator(BigDecimal::divide, other);
    }
    public BigNum divide(Object other, Integer beforeOperteScale) {
        return operator(BigDecimal::divide, other, beforeOperteScale);
    }

    public BigDecimal getValue() {
        return value;
    }
    public BigDecimal getValue(int scale) {
        return value.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    public double doubleValue() { return getValue().doubleValue(); }
    public double doubleValue(int scale) { return getValue(scale).doubleValue(); }
    public float floatValue() { return getValue().floatValue(); }
    public float floatValue(int scale) { return getValue(scale).floatValue(); }
    public int intValue() { return getValue().intValue(); }
    public long longValue() { return getValue().longValue(); }

    public Double getDouble() { return new Double(doubleValue()); }
    public Double getDouble(int scale) { return new Double(doubleValue(scale)); }
    public Float getFloat() { return new Float(floatValue()); }
    public Float getFloat(int scale) { return new Float(floatValue(scale)); }
    public Integer getInteger() { return new Integer(intValue()); }
    public Long getLong() { return new Long(longValue()); }

    public String toString(String format) {
        return new DecimalFormat(format).format(getValue());
    }
}
