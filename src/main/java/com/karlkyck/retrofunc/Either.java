package com.karlkyck.retrofunc;

import java.util.NoSuchElementException;
import java.util.Objects;

public abstract class Either<L, R> {

    public static <L, R> Either<L, R> right(final R right) {
        return new Right<>(right);
    }

    public static <L, R> Either<L, R> left(final L left) {
        return new Left<>(left);
    }

    public abstract L getLeft();

    public abstract R get();

    public R getOrElseGet(final Function<L, R> f) {
        if (isRight()) {
            return get();
        } else {
            return f.apply(getLeft());
        }
    }

    public abstract boolean isLeft();

    public abstract boolean isRight();

    public abstract <U> Either<U, R> mapLeft(final Function<L, U> f);

    public abstract <U> Either<L, U> map(final Function<R, U> f);

    static class Left<L, R> extends Either<L, R> {

        private final L left;

        private Left(final L left) {
            this.left = left;
        }

        @Override
        public L getLeft() {
            return left;
        }

        @Override
        public R get() {
            throw new NoSuchElementException();
        }

        @Override
        public boolean isLeft() {
            return true;
        }

        @Override
        public boolean isRight() {
            return false;
        }

        @Override
        public <U> Either<U, R> mapLeft(final Function<L, U> f) {
            return new Left<>(f.apply(left));
        }

        @SuppressWarnings("unchecked")
        @Override
        public <U> Either<L, U> map(final Function<R, U> f) {
            return (Either<L, U>) this;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Left<?, ?> left1 = (Left<?, ?>) o;
            return Objects.equals(left, left1.left);
        }

        @Override
        public int hashCode() {

            return Objects.hash(left);
        }
    }

    static class Right<L, R> extends Either<L, R> {

        private final R right;

        private Right(final R right) {
            this.right = right;
        }

        @Override
        public L getLeft() {
            throw new NoSuchElementException();
        }

        @Override
        public R get() {
            return right;
        }

        @Override
        public boolean isLeft() {
            return false;
        }

        @Override
        public boolean isRight() {
            return true;
        }

        @SuppressWarnings("unchecked")
        @Override
        public <U> Either<U, R> mapLeft(final Function<L, U> f) {
            return (Either<U, R>) this;
        }

        @Override
        public <U> Either<L, U> map(final Function<R, U> f) {
            return new Right<>(f.apply(right));
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Right<?, ?> right1 = (Right<?, ?>) o;
            return Objects.equals(right, right1.right);
        }

        @Override
        public int hashCode() {

            return Objects.hash(right);
        }
    }
}
