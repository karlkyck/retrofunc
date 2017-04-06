package com.karlkyck.retrofunc;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Copyright WithWoof 2017
 * Created by karlkyck on 07/03/17.
 */
public class OptionTest {

	@Test
	public void ofShouldInstantiateSome() {
		final String theValue = "value";
		final Option<String> some = Option.of(theValue);

		assertThat(some.isEmpty()).isFalse();
		assertThat(some.get()).isEqualTo(theValue);
	}

	@Test(expected = NullPointerException.class)
	public void someShouldThrowNullPointer() {
		Option.some(null);
	}

	@Test
	public void someShouldInstantiateSome() {
		final String theValue = "value";
		final Option<String> some = Option.some(theValue);

		assertThat(some.isEmpty()).isFalse();
		assertThat(some.get()).isEqualTo(theValue);
	}

	@Test
	public void noneShouldInstantiateNone() {
		final Option<String> none = Option.none();

		assertThat(none.isEmpty()).isTrue();
	}

	@Test
	public void ofShouldInstantiateNone() {
		final Option<String> none = Option.of(null);

		assertThat(none.isEmpty()).isTrue();
	}

	@Test
	public void someShouldGet() {
		final String theValue = "value";
		final Option<String> some = Option.of(theValue);

		assertThat(some.getOrElse("otherValue")).isEqualTo(theValue);
	}

	@Test
	public void someShouldGetSupplier() {
		final String theValue = "value";
		final Option<String> some = Option.of(theValue);

		assertThat(some.getOrElse(() -> "otherValue")).isEqualTo(theValue);
	}

	@Test
	public void noneShouldGetOrElse() {
		final Option<String> none = Option.of(null);

		String theValue = "value";
		assertThat(none.getOrElse(theValue)).isEqualTo(theValue);
	}

	@Test
	public void noneShouldGetSupplier() {
		final Option<String> some = Option.none();
		final String otherValue = "otherValue";

		assertThat(some.getOrElse(() -> otherValue)).isEqualTo(otherValue);
	}

	@Test
	public void itShouldRunIfIsEmpty() {

		boolean hasRun = false;

		Option
				.none()
				.<Runnable>map(account -> () -> {
				})
				.getOrElse(() -> {
					System.out.println("wooo");
				})
				.run();
	}
}
