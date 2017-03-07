package com.karlkyck.retrofunc;

/**
 * Copyright WithWoof 2017
 * Created by karlkyck on 07/03/17.
 */
final class None<T> extends Option<T> {

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public T get() {
		throw new NullPointerException();
	}

	@Override
	public T getOrElse(final T other) {
		return other;
	}

	@Override
	public T getOrElse(final Supplier<T> supplier) {
		return supplier.get();
	}

	@Override
	public Option<T> orElse(final Option<T> other) {
		return other;
	}

	@Override
	public Option<T> orElse(final Supplier<Option<T>> s) {
		return s.get();
	}

	@Override
	public <R> Option<R> map(final Function<T, R> f) {
		return new None<>();
	}

	@Override
	public <R> Option<R> flatMap(final Function<T, Option<R>> f) {
		return new None<>();
	}

	@Override
	public Option<T> peek(final Consumer<T> consumer) {
		return this;
	}
}
