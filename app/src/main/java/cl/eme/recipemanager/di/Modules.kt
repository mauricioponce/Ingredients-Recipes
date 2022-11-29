package cl.eme.recipemanager.di

import cl.eme.recipe.core.domain.RecipesRepository
import cl.eme.recipe.data.RecipesRepositoryImpl
import cl.eme.recipe.listing.domain.GetIngredientsUseCase
import cl.eme.recipe.listing.domain.GetIngredientsUseCaseImp
import cl.eme.recipe.listing.domain.GetRecipesUseCase
import cl.eme.recipe.listing.domain.GetRecipesUseCaseImp
import cl.eme.recipemanager.listing.ListingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // single instance of repository
    single<RecipesRepository> { RecipesRepositoryImpl() }

    // single instance of use cases
    single<GetRecipesUseCase> { GetRecipesUseCaseImp(get()) }
    single<GetIngredientsUseCase> { GetIngredientsUseCaseImp(get()) }


    // MyViewModel ViewModel
    viewModel { ListingViewModel(get()) }
}