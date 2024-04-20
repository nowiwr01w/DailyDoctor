package domain.usecase.brand

import domain.repository.brand.AppBrand
import com.nowiwr01p.model.usecase.UseCase

interface GetAppBrandUseCase: UseCase<Unit, AppBrand>