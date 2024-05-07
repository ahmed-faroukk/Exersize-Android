package com.farouk.exersize.features.plan.domain.repo

import com.farouk.exersize.features.plan.domain.Entitiy.GetPlanResponse

interface PlanRepo  {
    fun getTraineePlan( token : String) : GetPlanResponse

}