package xyz.megundo.icymoon.repository

import xyz.megundo.icymoon.api.InfoApiService


object RepositoryProvider {
    fun provideRepository(): InfoRepository {
        return InfoRepository(InfoApiService.provideRetrofit())
    }
}