package com.karlkyck.retrofunc;

/**
 * Copyright WithWoof 2017
 * Created by karlkyck on 16/01/17.
 */
final class TryFailure<T> extends Try<T> {

	private final Throwable throwable;

	TryFailure(final Throwable throwable) {
		this.throwable = throwable;
	}

	@Override
	public boolean failed() {
		return true;
	}

	@Override
	public boolean succeeded() {
		return false;
	}

	@Override
	public T get() {
		throw new IllegalStateException(throwable);
	}

	@Override
	public <R> Try<R> map(final CheckedFunction<T, R> func) {
		return Try.failure(throwable);
	}

	@Override
	public <R> Try<R> flatMap(final CheckedFunction<T, Try<R>> func) {
		return Try.failure(throwable);
	}

	@Override
	public Try<T> recover(final CheckedFunction<? super Throwable, T> func) {
		try {
			return Try.success(func.apply(throwable));
		} catch (final Throwable throwable) {
			return Try.failure(throwable);
		}
	}
}
