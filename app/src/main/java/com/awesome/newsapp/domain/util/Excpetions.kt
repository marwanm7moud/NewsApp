package com.awesome.newsapp.domain.util

open class NewsException(override val message: String?) : Exception(message)


class NetworkException(override val message: String?) : NewsException(message)

class EmptyDataException(override val message: String?) : NewsException(message)

class ServerErrorException(override val message: String?) : NewsException(message)

class UnknownErrorException(override val message: String?) : NewsException(message)
