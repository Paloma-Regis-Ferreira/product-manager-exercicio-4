package com.paloma.product_manager.application;

import com.paloma.product_manager.adapters.Utils.StringUtils;
import com.paloma.product_manager.adapters.dto.ProductDTO;
import com.paloma.product_manager.adapters.mapper.ProductMapper;
import com.paloma.product_manager.application.messaging.ProductProducerUseCase;
import com.paloma.product_manager.domain.exception.ProductAlreadyExistsException;
import com.paloma.product_manager.domain.model.ProductEntity;
import com.paloma.product_manager.domain.respository.ProductModelUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.paloma.product_manager.adapters.mapper.ProductMapper.convertDtoToEntity;
import static com.paloma.product_manager.adapters.mapper.ProductMapper.convertEntityToDto;

@Service
public class ProductService implements ProductServiceUseCase {

    @Autowired
    private ProductModelUseCase useCase;

    @Autowired
    private ProductProducerUseCase productProducer;

    @Override
    public ProductDTO getProductById(Long id) {
        ProductEntity entity = useCase.findById(id);
        return convertEntityToDto(entity);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return useCase.findAll().stream()
                .map(ProductMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO createProduct(ProductDTO newProduct) {
        String treatedProductName = StringUtils.treatmentString(newProduct.getName());

        Optional<ProductEntity> entity = useCase.findByName(treatedProductName);
        if (entity.isPresent()){
            throw new ProductAlreadyExistsException(entity.get().getId(), convertEntityToDto(entity.get()));
        }
        newProduct.setName(treatedProductName);

        ProductEntity productCreated = useCase.create(convertDtoToEntity(newProduct));
        productProducer.publishProductMessage(productCreated);

        return convertEntityToDto(productCreated);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO updateProduct) {
        String treatedProductName = StringUtils.treatmentString(updateProduct.getName());
        updateProduct.setName(treatedProductName);
        return convertEntityToDto(useCase.update(
                id, convertDtoToEntity(updateProduct)));
    }

    @Override
    public void deleteProduct(Long id) {
        useCase.delete(id);
    }

}
/**
 * Comentarios sobre o que foi aplicado de SOLID
 * Single Responsibility Principle (SRP): A classe ProductService é focada em realizar operações entre a camada de aplicação e a camada de domínio através do ProductModelUseCase;
 * O método treatmentString foi movido para uma classe util para que possa manter o principio de responsabilidade unica.
 * Dependency Inversion Principle (DIP): A ProductService depende da interface ProductModelUseCase, não de implementações concretas;
 * Passou a depender também de uma interface para a implementação da mensageria.
 *
 * Além dos princípios SOLID, o código também segue algumas boas práticas de Clean Code:
 * Os nomes dos métodos são descritivos e expressam claramente sua função
 * No método createProduct, uma exceção ProductAlreadyExistsException é lançada se um produto com o mesmo nome já existir, demonstrando um bom manejo de exceções personalizadas.
 */