package com.karlkyck.retrofunc;

/**
 * Copyright WithWoof 2017
 * Created by karlkyck on 07/03/17.
 */
@FunctionalInterface
public interface Function<T, R> {
	R apply(final T t);
}
