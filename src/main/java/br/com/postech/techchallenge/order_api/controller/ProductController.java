package br.com.postech.techchallenge.order_api.controller;

import br.com.postech.techchallenge.order_api.dto.product.CreateProductDto;
import br.com.postech.techchallenge.order_api.dto.product.ProductDto;
import br.com.postech.techchallenge.order_api.dto.product.UpdateProductDto;
import br.com.postech.techchallenge.order_api.service.internalService.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> Create(@RequestBody @Valid CreateProductDto createDto) throws EntityNotFoundException {
        var dto = productService.Create(createDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> GetAllByProductCategoryName(@RequestParam String categoryName) {
        var dtos = productService.Find(categoryName);
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> Update(@PathVariable Long id, @RequestBody UpdateProductDto updateDto) throws EntityNotFoundException {

        try {
            productService.Update(id, updateDto);
            return ResponseEntity.status(HttpStatus.OK.value()).build();

        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Delete(@PathVariable Long id) throws EntityNotFoundException {
        try {
            productService.Delete(id);
            return ResponseEntity.status(HttpStatus.OK.value()).build();

        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
