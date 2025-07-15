package com.csb.data

import com.csb.data.repositories.HomeRepositoryImpl
import com.csb.data.repositories.RecipeRepositoryImpl
import com.csb.data.repositories.UploadRecipeRepositoryImpl
import com.csb.domain.repositories.HomeRepository
import com.csb.domain.repositories.RecipeRepository
import com.csb.domain.repositories.UploadRecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//의존성 주입(Dependency Injection)
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    //HomeRepository 바인딩
    @Binds
    @Singleton
    abstract fun bindHomeRepository(
        impl: HomeRepositoryImpl
    ): HomeRepository

    //RecipeRepository 바인딩
    @Binds
    @Singleton
    abstract fun bindRecipeRepository(
        impl: RecipeRepositoryImpl
    ): RecipeRepository

    //UploadRecipeRepository 바인딩
    @Binds
    @Singleton
    abstract fun bindUploadRecipeRepository(
        impl: UploadRecipeRepositoryImpl
    ): UploadRecipeRepository
}