package com.karlkyck.retrofunc.common;

/**
 * Copyright WithWoof 2017
 * Created by karlkyck on 16/01/17.
 */
public final class TrySuccess<T> extends Try<T> {

	private final T value;

	TrySuccess(final T value) {
		this.value = value;
	}

	@Override
	public boolean failed() {
		return false;
	}

	@Override
	public boolean succeeded() {
		return true;
	}

	@Override
	public T get() {
		return value;
	}

	@Override
	public <R> Try<R> map(final CheckedFunction<T, R> func) {
		try {
			return Try.success(func.apply(value));
		} catch (final Throwable t) {
			return Try.failure(t);
		}
	}

	@Override
	public <R> Try<R> flatMap(final CheckedFunction<T, Try<R>> func) {
		try {
			return func.apply(value);
		} catch (final Throwable t) {
			return Try.failure(t);
		}
	}

	@Override
	public Try<T> recover(final CheckedFunction<? super Throwable, T> func) {
		return this;
	}
}
