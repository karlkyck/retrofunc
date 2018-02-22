package com.karlkyck.retrofunc;

import java.util.NoSuchElementException;

public abstract class Either<L, R> {

    public static <L, R> Either<L, R> right(final R right) {
        return new Right<>(right);
    }

    public static <L, R> Either<L, R> left(final L left) {
        return new Left<>(left);
    }

    public abstract L getLeft();

    public abstract R get();

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
    }
}
