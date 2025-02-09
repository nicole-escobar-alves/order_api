package br.com.postech.techchallenge.order_api.service.internalService;

import br.com.postech.techchallenge.order_api.dto.product.CreateProductDto;
import br.com.postech.techchallenge.order_api.dto.product.ProductDto;
import br.com.postech.techchallenge.order_api.dto.product.UpdateProductDto;
import br.com.postech.techchallenge.order_api.enums.ProductCategory;
import br.com.postech.techchallenge.order_api.infrastructure.entities.ProductEntity;
import br.com.postech.techchallenge.order_api.infrastructure.repositories.IProductJpaRepository;
import br.com.postech.techchallenge.order_api.mapper.IProductMapper;
import br.com.postech.techchallenge.order_api.models.Product;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    IProductMapper productMapper;

    @Autowired
    IProductJpaRepository productRepository;

    public ProductDto Create(CreateProductDto productDto) {

        Product product = productMapper.toProduct(productDto);
        product.setEstimatedTime(Duration.ofMinutes(productDto.getEstimatedTime()));

        ProductEntity entity = productMapper.toEntity(product);
        productRepository.save(entity);

        return productMapper.toProductDto(product);
    }

    public List<ProductDto> FindByName(String name) {

        List<ProductEntity> entities = productRepository.findAllByIsActiveTrueAndProductCategory(ProductCategory.fromDisplayName(name));

        List<Product> productDtos = productMapper.toDomains(entities);

        return productMapper.toProductListDto(productDtos);
    }

    public Product FindById(Long id) {

        ProductEntity productEntity = productRepository.findById(id).get();

        return productMapper.toDomain(productEntity);
    }

    public void Delete(Long id) throws EntityNotFoundException {

        if (!productRepository.existsById(id)) throw new EntityNotFoundException("The product was not found.");

        productRepository.deleteById(id);

    }

    public void Update(Long idProduct, UpdateProductDto productDto) throws EntityNotFoundException {

        ProductEntity entity = productRepository.findById(idProduct).get();

        Product product = productMapper.toDomain(entity);
        product.update(productDto);

        productRepository.save(entity);
    }

}
