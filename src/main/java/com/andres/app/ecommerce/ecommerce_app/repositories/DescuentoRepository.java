package com.andres.app.ecommerce.ecommerce_app.repositories;

import com.andres.app.ecommerce.ecommerce_app.models.Descuentos;
import org.springframework.data.repository.CrudRepository;

public interface DescuentoRepository extends CrudRepository<Descuentos, Long> {
}
