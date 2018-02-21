package com.karlkyck.retrofunc;

import java.util.NoSuchElementException;

public interface Either<L, R> {

    static <L, R> Either<L, R> right(final R right) {
        return new Right<>(right);
    }

    static <L, R> Either<L, R> left(final L left) {
        return new Left<>(left);
    }

    L getLeft();

    R get();

    boolean isLeft();

    boolean isRight();

    <U> Either<U, R> mapLeft(final Function<L, U> f);

    <U> Either<L, U> map(final Function<R, U> f);


    class Left<L, R> implements Either<L, R> {

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

    class Right<L, R> implements Either<L, R> {

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

        @SuppressWarnings("unchecked")
        @Override
        public <U> Either<L, U> map(final Function<R, U> f) {
            return (Either<L, U>) new Right<>(f.apply(right));
        }
    }
}
