package cl.eme.recipe.applayout.di

import cl.eme.recipe.applayout.listing.ListingViewModel
import cl.eme.recipe.applayout.readytoeat.listing.ListingReadyToEatFragment
import cl.eme.recipe.applayout.readytoeat.listing.ReadyToEatViewModel
import cl.eme.recipe.core.domain.RecipesRepository
import cl.eme.recipe.data.ReadyToEatRepositoryImpl
import cl.eme.recipe.data.RecipesRepositoryImpl
import cl.eme.recipe.listing.domain.GetRecipesUseCase
import cl.eme.recipe.listing.domain.GetRecipesUseCaseImp
import cl.eme.recipe.ready2eat.GetReadyToEatUseCase
import cl.eme.recipe.ready2eat.GetReadyToEatUseCaseImp
import cl.eme.recipe.ready2eat.ReadyToEatRepository
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val listingAppModule = module {
    // single instance of CounterRepository
    single<RecipesRepository> { RecipesRepositoryImpl() }
    single<ReadyToEatRepository> { ReadyToEatRepositoryImpl() }

    // single instance of use cases
    single<GetRecipesUseCase> { GetRecipesUseCaseImp(get()) }
    single<GetReadyToEatUseCase> { GetReadyToEatUseCaseImp(get()) }

    // MyViewModel ViewModel
    viewModel { ListingViewModel(get()) }
    viewModel { ReadyToEatViewModel(get()) }
}