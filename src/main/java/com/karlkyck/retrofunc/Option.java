package com.karlkyck.retrofunc;

/**
 * Copyright WithWoof 2017
 * Created by karlkyck on 07/03/17.
 */
public abstract class Option<T> {

	public abstract boolean isEmpty();

	public abstract T get();

	public abstract T getOrElse(T value);

	public abstract T getOrElse(Supplier<T> s);

	public abstract Option<T> orElse(Option<T> other);

	public abstract Option<T> orElse(Supplier<Option<T>> s);

	public abstract <R> Option<R> map(Function<T, R> f);

	public abstract <R> Option<R> flatMap(Function<T, Option<R>> function);

	public abstract Option<T> peek(Consumer<T> consumer);

	public abstract Option<T> ifEmpty(Runnable runnable);

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

	public static Option<Tuple0> all() {
		return of(Tuple0.instance());
	}

	public static <T1> Option<Tuple1<T1>> all(final Option<T1> maybeT1) {
		return maybeT1.map(o -> Tuple.of(maybeT1.get()));
	}

	public static <T1, T2> Option<Tuple2<T1, T2>> all(final Option<T1> maybeT1, final Option<T2> maybeT2) {
		return maybeT1
				.flatMap(o -> maybeT2)
				.map(o -> Tuple.of(maybeT1.get(), maybeT2.get()));
	}

	public static <T1, T2, T3> Option<Tuple3<T1, T2, T3>> all(
			final Option<T1> maybeT1,
			final Option<T2> maybeT2,
			final Option<T3> maybeT3
	) {
		return maybeT1
				.flatMap(o -> maybeT2)
				.flatMap(o -> maybeT3)
				.map(o -> Tuple.of(maybeT1.get(), maybeT2.get(), maybeT3.get()));
	}

	public static <T1, T2, T3, T4> Option<Tuple4<T1, T2, T3, T4>> all(
			final Option<T1> maybeT1,
			final Option<T2> maybeT2,
			final Option<T3> maybeT3,
			final Option<T4> maybeT4
	) {
		return maybeT1
				.flatMap(o -> maybeT2)
				.flatMap(o -> maybeT3)
				.flatMap(o -> maybeT4)
				.map(o -> Tuple.of(maybeT1.get(), maybeT2.get(), maybeT3.get(), maybeT4.get()));
	}
}
