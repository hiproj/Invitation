package com.invitation;


public interface ItfEvent{
	public void splashEnd();
	public void cardSelect(final int idx);
	public void setColorChange(final int color);
	public void startStyleChange();
	public void selectTextStyle(int id);
	public void setTextSize(int size);
}
