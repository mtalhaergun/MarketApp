package com.quenhwyfar.marketapp.domain.mapper

import com.quenhwyfar.marketapp.core.mapper.DtoMapper
import com.quenhwyfar.marketapp.data.remote.dto.ProductsDto
import com.quenhwyfar.marketapp.domain.uimodel.Products

class ProductsDtoMapper : DtoMapper<ProductsDto,Products> {
    override fun toDomain(dto: ProductsDto): Products {
        return Products(
            id = dto.id,
            currency = dto.currency,
            imageUrl = dto.imageUrl,
            name = dto.name,
            price = dto.price,
            stock = dto.stock
        )
    }

    fun toDomainList(dtoList : List<ProductsDto>) : List<Products>{
        return dtoList.map { toDomain(it) }
    }

}