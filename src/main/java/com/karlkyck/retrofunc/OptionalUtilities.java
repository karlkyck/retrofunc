package com.karlkyck.retrofunc;

import java8.util.Optional;

/**
 * Copyright WithWoof 2017
 * Created by karlkyck on 10/03/17.
 */
public class OptionalUtilities {

	public static <T1, T2, T3, T4> Optional<Tuple4<T1, T2, T3, T4>> all(
			final Optional<T1> maybeT1,
			final Optional<T2> maybeT2,
			final Optional<T3> maybeT3,
			final Optional<T4> maybeT4
	) {
		return maybeT1
				.flatMap(o -> maybeT2)
				.flatMap(o -> maybeT3)
				.flatMap(o -> maybeT4)
				.map(o -> Tuple.of(maybeT1.get(), maybeT2.get(), maybeT3.get(), maybeT4.get()));
	}
}
