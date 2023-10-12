package com.quenhwyfar.marketapp.core.mapper

interface DtoMapper <Dto,Domain>{
    fun toDomain(dto : Dto) : Domain
}