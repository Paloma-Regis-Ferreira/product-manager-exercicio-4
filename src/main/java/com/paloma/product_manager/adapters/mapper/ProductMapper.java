package com.paloma.product_manager.adapters.mapper;

import com.paloma.product_manager.adapters.dto.ProductDTO;
import com.paloma.product_manager.domain.model.ProductEntity;

public class ProductMapper {

    public static ProductDTO convertEntityToDto(ProductEntity entity){
        return ProductDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .build();
    }

    public static ProductEntity convertDtoToEntity(ProductDTO dto){
        return ProductEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .build();
    }
}

/**
 * Comentarios sobre o que foi aplicado de SOLID
 * Single Responsibility Principle (SRP): A classe ProductMapper tem uma responsabilidade única de converter objetos entre ProductEntity e ProductDTO. Cada método (convertEntityToDto e convertDtoToEntity) executa uma única operação de conversão.
 * Open/Closed Principle (OCP): Os métodos estão fechados para alteração. Qualquer nova conversão deve ser adicionada à um método novo.
 *
 * Além dos princípios SOLID, o código também segue algumas boas práticas de Clean Code:
 * Os nomes dos métodos são descritivos e expressam claramente sua função
 * O uso do padrão Builder (ProductDTO.builder() e ProductEntity.builder()) facilita a criação de objetos complexos, mantendo o código limpo e legível.
 */
