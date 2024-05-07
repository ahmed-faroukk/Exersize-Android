package com.farouk.exersize.features.plan.domain.Entitiy

data class GetPlanResponse(
    val error_msg: String,
    val msg: List<Msg>,
    val status: Boolean
)