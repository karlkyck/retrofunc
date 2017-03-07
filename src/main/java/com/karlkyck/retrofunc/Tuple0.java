package com.karlkyck.retrofunc;

/**
 * Copyright WithWoof 2017
 * Created by karlkyck on 07/03/17.
 */
public class Tuple0 extends Tuple {

	public static final Tuple0 INSTANCE = new Tuple0();

	private Tuple0() {
	}

	public static Tuple0 instance() {
		return INSTANCE;
	}
}
