package nowiwr01p.daily.doctor.tg_sms.domain.usecase

import com.nowiwr01p.model.usecase.UseCase
import nowiwr01p.daily.doctor.tg_sms.domain.api.requests.VerificationRequest
import nowiwr01p.daily.doctor.tg_sms.domain.api.response.VerificationResponseWrap

interface TgSendVerificationCodeUseCase: UseCase<VerificationRequest, VerificationResponseWrap>