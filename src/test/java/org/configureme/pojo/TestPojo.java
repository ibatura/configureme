package org.configureme.pojo;

import java.util.Arrays;

public class TestPojo {
	public enum EnumType {
		A, B, C
	}
	private short shortValue;
	private long longValue;
	private int intValue;
	private boolean booleanValue;
	private String stringValue;
	private byte byteValue;
	private float floatValue;
	private double doubleValue ;
	private int onlyInA;
	private int onlyInB;
    private InnerTestPojo innerValue;
    private InnerTestPojo[] innerValueArray;
    private EnumType enumValue;

	@Override public String toString(){
		String ret = "";
		ret += "short: "+shortValue;
		ret += " long: "+longValue;
		ret += " int: "+intValue;
		ret += " boolean: "+booleanValue;
		ret += " string: "+stringValue;
		ret += " byte: "+byteValue;
		ret += " float: "+floatValue;
		ret += " double: "+doubleValue;
		ret += " onlyInA: "+onlyInA;
		ret += " onlyInB: "+onlyInB;
		ret += " innerValue: "+innerValue;
		ret += " innerValueArray: "+Arrays.toString(innerValueArray);
		ret += " enumValue: "+enumValue;
		return ret;
	}

	public short getShortValue() {
		return shortValue;
	}
	public void setShortValue(short shortValue) {
		this.shortValue = shortValue;
	}
	public long getLongValue() {
		return longValue;
	}
	public void setLongValue(long longValue) {
		this.longValue = longValue;
	}
	public int getIntValue() {
		return intValue;
	}
	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}
	public boolean getBooleanValue() {
		return booleanValue;
	}
	public void setBooleanValue(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}
	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	public byte getByteValue() {
		return byteValue;
	}
	public void setByteValue(byte byteValue) {
		this.byteValue = byteValue;
	}
	public float getFloatValue() {
		return floatValue;
	}
	public void setFloatValue(float floatValue) {
		this.floatValue = floatValue;
	}
	public double getDoubleValue() {
		return doubleValue;
	}
	public void setDoubleValue(double doubleValue) {
		this.doubleValue = doubleValue;
	}
	public int getOnlyInA() {
		return onlyInA;
	}
	public void setOnlyInA(int onlyInA) {
		this.onlyInA = onlyInA;
	}
	public int getOnlyInB() {
		return onlyInB;
	}
	public void setOnlyInB(int onlyInB) {
		this.onlyInB = onlyInB;
	}
    public InnerTestPojo getInnerValue() {
        return innerValue;
    }
    public void setInnerValue(InnerTestPojo innerValue) {
        this.innerValue = innerValue;
    }
    public InnerTestPojo[] getInnerValueArray() {
        return innerValueArray;
    }
    public void setInnerValueArray(InnerTestPojo[] innerValueArray) {
        this.innerValueArray = innerValueArray;
    }
	public EnumType getEnumValue() {
		return enumValue;
	}
	public void setEnumValue(EnumType enumValue) {
		this.enumValue = enumValue;
	}
}
