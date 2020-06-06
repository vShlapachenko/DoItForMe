package com.example.doitforme.data;

/**
 * A generic class that holds a result success w/ data or an error exception.
 */
public interface Result<T> {
    // hide the private constructor to limit subclass types (Success, Error)


    // Success sub-class
    public final static class Success<T> implements Result<T> {
        private T data;

        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }

        @Override
        public String toString() {
                return "Success[data=" + data.toString() + "]";
        }
    }

    // Error sub-class
    public final static class Error<T> implements Result<T> {
        private Exception error;

        public Error(Exception error) {
            this.error = error;
        }

        public Exception getError() {
            return this.error;
        }

        @Override
        public String toString() {
                return "Error[exception=" + error.toString() + "]";
        }
    }
}