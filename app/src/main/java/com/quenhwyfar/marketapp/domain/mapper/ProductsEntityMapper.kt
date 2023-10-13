package com.quenhwyfar.marketapp.domain.mapper

import com.quenhwyfar.marketapp.core.mapper.EntityMapper
import com.quenhwyfar.marketapp.data.local.entity.ProductsEntity
import com.quenhwyfar.marketapp.domain.uimodel.Products

class ProductsEntityMapper : EntityMapper<ProductsEntity, Products> {
    override fun toDomain(entity: ProductsEntity): Products {
        return Products(
            id = entity.id,
            currency = entity.currency,
            imageUrl = entity.imageUrl,
            name = entity.name,
            price = entity.price,
            stock = entity.stock
        )
    }

    override fun fromDomain(domain: Products): ProductsEntity {
        return ProductsEntity(
                id = domain.id!!,
                currency = domain.currency,
                imageUrl = domain.imageUrl,
                name = domain.name,
                price = domain.price,
                stock = domain.stock
            )
    }

    fun toDomainList(entities : List<ProductsEntity>) : List<Products> {
        return entities.map { toDomain(it) }
    }
}


