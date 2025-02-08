package br.com.postech.techchallenge.order_api.controller;

import br.com.postech.techchallenge.order_api.dto.addon.AddonDto;
import br.com.postech.techchallenge.order_api.dto.addon.CreateAddonDto;
import br.com.postech.techchallenge.order_api.dto.addon.UpdateAddonDto;
import br.com.postech.techchallenge.order_api.service.internalService.AddonService;
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
@RequestMapping("/addon")
public class AddonController {

    @Autowired
    private AddonService addonService;

    @PostMapping
    public ResponseEntity<String> Create(@RequestBody @Valid CreateAddonDto dto) {
        try {
            addonService.Create(dto);
            return ResponseEntity.status(HttpStatus.CREATED.value()).build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<AddonDto>> ListAllByProductCategory(@RequestParam String categoryName) throws EntityNotFoundException {
        try {
            var response = addonService.FindAllByProductCategory(categoryName);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddonDto> Update(@PathVariable Long id, @RequestBody @Valid UpdateAddonDto dto) throws EntityNotFoundException {
        try {
            addonService.Update(id, dto);
            return ResponseEntity.status(HttpStatus.OK.value()).build();

        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws EntityNotFoundException {
        try {
            addonService.Delete(id);
            return ResponseEntity.status(HttpStatus.OK.value()).build();

        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
