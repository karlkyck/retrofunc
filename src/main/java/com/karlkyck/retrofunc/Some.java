package com.karlkyck.retrofunc;

/**
 * Copyright WithWoof 2017
 * Created by karlkyck on 07/03/17.
 */
final class Some<T> extends Option<T> {

	private final T value;

	Some(final T value) {
		this.value = value;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public T get() {
		return value;
	}

	@Override
	public T getOrElse(final T value) {
		return this.value;
	}

	@Override
	public T getOrElse(final Supplier<T> s) {
		return this.value;
	}

	@Override
	public Option<T> orElse(final Option<T> other) {
		return this;
	}

	@Override
	public Option<T> orElse(final Supplier<Option<T>> supplier) {
		return this;
	}

	@Override
	public <R> Option<R> map(final Function<T, R> f) {
		return of(f.apply(value));
	}

	@Override
	public <R> Option<R> flatMap(final Function<T, Option<R>> f) {
		return f.apply(value);
	}

	@Override
	public Option<T> peek(final Consumer<T> consumer) {
		consumer.accept(value);
		return this;
	}

	@Override
	public Option<T> ifEmpty(final Runnable runnable) {
		return this;
	}
}
