package com.karlkyck.retrofunc;

/**
 * Copyright WithWoof 2017
 * Created by karlkyck on 16/01/17.
 */
public abstract class Try<T> {

	public abstract boolean failed();

	public abstract boolean succeeded();

	public abstract T get();

	public abstract <R> Try<R> map(final CheckedFunction<T, R> f);

	public abstract <R> Try<R> flatMap(final CheckedFunction<T, Try<R>> f);

	public abstract Try<T> recover(final CheckedFunction<? super Throwable, T> f);

	public static <T> Try<T> of(final CheckedSupplier<T> supplier) {
		try {
			return success(supplier.get());
		} catch (final Throwable t) {
			return failure(t);
		}
	}

	public static <T> Try<T> success(final T value) {
		return new TrySuccess<>(value);
	}

	public static <T> Try<T> failure(final Throwable t) {
		return new TryFailure<>(t);
	}

	@FunctionalInterface
	public interface CheckedSupplier<T> {
		T get() throws Throwable;
	}

	@FunctionalInterface
	public interface CheckedFunction<T, R> {
		R apply(T t) throws Throwable;
	}
}
