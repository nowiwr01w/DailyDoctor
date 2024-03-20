package domain.usecase.brand

import domain.repository.brand.AppBrand
import domain.usecase.UseCase

interface GetAppBrandUseCase: UseCase<Unit, AppBrand>