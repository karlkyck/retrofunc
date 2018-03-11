package com.karlkyck.retrofunc;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.NoSuchElementException;

public class EitherTest {

    @Test
    public void shouldBeLeft() {
        final Either<String, String> left = Either.left("Left");
        Assertions.assertThat(left.isLeft()).isTrue();
    }

    @Test
    public void shouldNotBeRight() {
        final Either<String, String> left = Either.left("Left");
        Assertions.assertThat(left.isRight()).isFalse();
    }

    @Test
    public void shouldBeRight() {
        final Either<String, String> right = Either.right("Right");
        Assertions.assertThat(right.isRight()).isTrue();
    }

    @Test
    public void shouldNotBeLeft() {
        final Either<String, String> right = Either.right("Right");
        Assertions.assertThat(right.isLeft()).isFalse();
    }

    @Test
    public void shouldMapRight() {
        final Either<String, String> right = Either.right("Right");
        final Either<String, String> newRight = right.map(s -> "New Right");
        Assertions.assertThat(newRight.get()).isEqualTo("New Right");
    }

    @Test
    public void shouldNotMapRight() {
        final Either<String, String> left = Either.left("Left");
        final Either<String, String> newRight = left.map(s -> "New Right");
        Assertions.assertThat(newRight.getLeft()).isEqualTo("Left");
    }

    @Test
    public void shouldMapLeft() {
        final Either<String, String> left = Either.left("Left");
        final Either<String, String> newLeft = left.mapLeft(s -> "New Left");
        Assertions.assertThat(newLeft.getLeft()).isEqualTo("New Left");
    }

    @Test
    public void shouldNotMapLeft() {
        final Either<String, String> right = Either.right("Right");
        final Either<String, String> newRight = right.mapLeft(s -> "New Left");
        Assertions.assertThat(newRight.get()).isEqualTo("Right");
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementExceptionOnGetLeft() {
        final Either<String, String> right = Either.right("Right");
        right.getLeft();
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementExceptionOnGet() {
        final Either<String, String> left = Either.left("Left");
        left.get();
    }

    @Test
    public void shouldEqualLeft() {
        final Either<String, Object> either = Either.left("left");
        Assertions.assertThat(either).isEqualTo(Either.left("left"));
    }

    @Test
    public void shouldNotEqualLeft() {
        final Either<String, Object> either = Either.left("left");
        Assertions.assertThat(either).isNotEqualTo(Either.right("left"));
    }

    @Test
    public void shouldEqualRight() {
        final Either<String, String> either = Either.right("right");
        Assertions.assertThat(either).isEqualTo(Either.right("right"));
    }

    @Test
    public void shouldNotEqualRight() {
        final Either<String, String> either = Either.right("right");
        Assertions.assertThat(either).isNotEqualTo(Either.left("right"));
    }

    @Test
    public void shouldOrElseGet() {
        final Either<Throwable, String> either = Either.left(new RuntimeException());
        Assertions.assertThat(either.getOrElseGet(e -> "works")).isEqualTo("works");
    }
}
