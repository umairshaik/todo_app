package com.example.todoapp.domain.mapper

interface IDomainMapper<SourceModel, DomainModel> {

    fun mapToDomainModel(sourceModel: SourceModel): DomainModel

    fun mapFromDomainModel(domainModel: DomainModel): SourceModel
}