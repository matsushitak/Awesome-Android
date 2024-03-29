package com.trickring.app_mvvm_usecase.domain

/**
 * Result pattern class.　Respected [kotlin.Result] class.
 */
sealed class Result<T> {

    /**
     * Success data class.
     *
     * @param value data
     */
    data class Success<T>(val value: T) : Result<T>()

    /**
     * Failure data class.
     *
     * @param error error
     */
    data class Failure<T>(val error: Throwable) : Result<T>()

    /**
     * Success flag
     */
    val isSuccess: Boolean get() = this is Success

    /**
     * Failure flag
     */
    val isFailure: Boolean get() = this is Failure

    /**
     * Get value if result is success.
     *
     * @return value
     */
    fun getValueOrNull(): T? = (this as? Success)?.value

    /**
     * Get error if result is failure.
     *
     * @return value
     */
    fun getThrowableOrNull(): Throwable? = (this as? Failure)?.error

    /**
     * This is called if result is success.
     *
     * @param block block
     * @return Result
     */
    inline fun onSuccess(block: (value: T) -> Unit): Result<T> {
        if (this is Success) block(value)
        return this
    }

    /**
     * This is called if result is failure.
     *
     * @param block block
     * @return Result
     */
    inline fun onFailure(block: (e: Throwable) -> Unit): Result<T> {
        if (this is Failure) block(error)
        return this
    }

    companion object {

        /**
         * Create Success
         *
         * @param value Data
         * @return Result
         */
        fun <T> success(value: T): Result<T> = Success(value)

        /**
         * Create Failure
         *
         * @param error Error
         * @return Result
         */
        fun <T> failure(error: Throwable): Result<T> = Failure(error)
    }
}

/**
 * Calls the specified function [block] and returns its encapsulated result if invocation was successful,
 * catching and encapsulating any thrown exception as a failure.
 *
 * @param T value
 * @param block block
 * @return Result
 */
inline fun <T> result(block: () -> T): Result<T> {
    return try {
        Result.success(block())
    } catch (e: Throwable) {
        Result.failure(e)
    }
}
