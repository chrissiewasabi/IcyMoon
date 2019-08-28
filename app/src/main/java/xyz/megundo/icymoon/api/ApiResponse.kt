package xyz.megundo.icymoon.api

import xyz.megundo.icymoon.data.Information

class ApiResponse {
    var info: Information? = null
    private var error: Throwable? = null

    constructor(information: Information) {
        this.info = information
        this.error = null
    }

    constructor(error: Throwable) {
        this.error = error
        this.info = null
    }

    fun getInformation(): Information? {
        return info
    }

    fun setInformation(information: Information) {
        this.info = information
    }

    fun getError(): Throwable? {
        return error
    }

    fun setError(error: Throwable) {
        this.error = error
    }
}