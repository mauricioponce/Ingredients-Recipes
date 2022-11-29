package cl.eme.recipe.applayout.di

import cl.eme.recipe.applayout.listing.ListingViewModel
import cl.eme.recipe.core.domain.RecipesRepository
import cl.eme.recipe.data.RecipesRepositoryImpl
import cl.eme.recipe.listing.domain.GetRecipesUseCase
import cl.eme.recipe.listing.domain.GetRecipesUseCaseImp
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val listingAppModule = module {
    // single instance of CounterRepository
    single<RecipesRepository> { RecipesRepositoryImpl() }

    // single instance of use cases
    single<GetRecipesUseCase> { GetRecipesUseCaseImp(get()) }

    // MyViewModel ViewModel
    viewModel { ListingViewModel(get()) }
}