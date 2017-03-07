package com.karlkyck.retrofunc;

/**
 * Copyright WithWoof 2017
 * Created by karlkyck on 07/03/17.
 */
public abstract class Option<T> {

	public abstract boolean isEmpty();

	public abstract T get();

	public abstract T getOrElse(final T value);

	public abstract T getOrElse(final Supplier<T> s);

	public abstract <R> Option<R> map(final Function<T, R> f);

	public abstract <R> Option<R> flatMap(final Function<T, Option<R>> f);

	public static <T> Option<T> of(final T value) {
		if (value == null)
			return new None<>();
		else
			return new Some<>(value);
	}

	public static <T> Option<T> none() {
		return new None<>();
	}

	public static <T> Option<T> some(final T value) {
		if (value == null)
			throw new NullPointerException("Value passed to some() cannot not be null");
		return of(value);
	}
}
