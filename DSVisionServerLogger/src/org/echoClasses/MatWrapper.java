package org.echoClasses;

import java.io.Serializable;

import org.opencv.core.MatOfByte;

public class MatWrapper implements Serializable {

	MatOfByte buff;

	public MatOfByte getBuff() {
		return buff;
	}

	public void setBuff(MatOfByte buff) {
		this.buff = buff;
	}
}
