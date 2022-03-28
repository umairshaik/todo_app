package com.example.todoapp.domain

import com.example.todoapp.domain.mapper.IDomainMapper
import com.example.todoapp.domain.service.entity.UiError
import com.example.todoapp.domain.service.entity.toUiError

sealed class DomainWrapper<DomainModel> {
    data class Success<DomainModel>(val data: DomainModel) : DomainWrapper<DomainModel>()
    data class Error<DomainModel>(val uiError: UiError) : DomainWrapper<DomainModel>()
}

fun <ResponseModel, DomainModel> TaskResponse<ResponseModel>.mapToDomainWrapper(
    domainMapper: IDomainMapper<ResponseModel, DomainModel>
): DomainWrapper<DomainModel> {
    return when (this) {
        is TaskResponse.Success -> DomainWrapper.Success(domainMapper.mapToDomainModel(this.data))
        is TaskResponse.Error -> DomainWrapper.Error(this.throwable.toUiError())
    }
}

fun <R, DomainModel> DomainWrapper<DomainModel>.map(block: (DomainWrapper<DomainModel>) -> R): R {
    return block(this)
}