package com.karlkyck.retrofunc;

import java.util.List;

/**
 * Copyright WithWoof 2017
 * Created by karlkyck on 07/03/17.
 */
public class ListUtilities {
	/**
	 * Applies f to each item in the supplied list
	 *
	 * @param list   List to iterate over
	 * @param action Consumer to against which each element in the supplied List is applied
	 * @param <T>    Type of elements within the List
	 */
	public static <T, R> void forEach(List<T> list, Consumer<T> action) {
		for (T t : list)
			action.accept(t);
	}
}
