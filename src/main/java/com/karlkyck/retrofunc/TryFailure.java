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
		if (throwable instanceof RuntimeException)
			throw (RuntimeException) throwable;
		throw new IllegalStateException(throwable);
	}

	@Override
	public <R> Try<R> map(final CheckedFunction<T, R> function) {
		return Try.failure(throwable);
	}

	@Override
	public <R> Try<R> flatMap(final CheckedFunction<T, Try<R>> function) {
		return Try.failure(throwable);
	}

	@Override
	public Try<T> andThen(final Consumer<T> consumer) {
		return this;
	}

	@Override
	public Try<T> andThenTry(final CheckedConsumer<T> consumer) {
		return this;
	}

	@Override
	public Try<T> recover(final CheckedFunction<? super Throwable, T> function) {
		try {
			return Try.success(function.apply(throwable));
		} catch (final Throwable throwable) {
			return Try.failure(throwable);
		}
	}
}
