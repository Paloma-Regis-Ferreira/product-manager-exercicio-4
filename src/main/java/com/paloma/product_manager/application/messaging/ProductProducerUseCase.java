package com.paloma.product_manager.application.messaging;

import com.paloma.product_manager.domain.model.ProductEntity;

public interface ProductProducerUseCase {

    public void publishProductMessage(ProductEntity entity);
}
