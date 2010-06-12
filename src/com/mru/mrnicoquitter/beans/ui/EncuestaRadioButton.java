package com.mru.mrnicoquitter.beans.ui;

import android.content.Context;
import android.widget.RadioButton;

public class EncuestaRadioButton extends RadioButton {
	private Integer value;
	public EncuestaRadioButton(Context context) {
		super(context);
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}

}
