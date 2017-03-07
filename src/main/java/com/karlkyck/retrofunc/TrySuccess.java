package com.karlkyck.retrofunc;

/**
 * Copyright WithWoof 2017
 * Created by karlkyck on 16/01/17.
 */
final class TrySuccess<T> extends Try<T> {

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
			return success(func.apply(value));
		} catch (final Throwable t) {
			return failure(t);
		}
	}

	@Override
	public <R> Try<R> flatMap(final CheckedFunction<T, Try<R>> func) {
		try {
			return func.apply(value);
		} catch (final Throwable t) {
			return failure(t);
		}
	}

	@Override
	public Try<T> andThen(final Consumer<T> consumer) {
		return andThenTry(consumer::accept);
	}

	@Override
	public Try<T> andThenTry(final CheckedConsumer<T> consumer) {
		try {
			consumer.accept(value);
			return this;
		} catch (final Throwable t) {
			return Try.failure(t);
		}
	}

	@Override
	public Try<T> recover(final CheckedFunction<? super Throwable, T> func) {
		return this;
	}
}
