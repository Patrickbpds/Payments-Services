package EventPay.Api_Boleto.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import EventPay.Api_Boleto.dto.BoletoDTO;
import EventPay.Api_Boleto.dto.BoletoRequestDTO;
import EventPay.Api_Boleto.service.BoletoService;

@RestController
@RequestMapping("/api/boleto")
public class BoletoController {
    private final BoletoService boletoService;

    public BoletoController(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @GetMapping("/{barcode}")
    public ResponseEntity<BoletoDTO> findBoletoByBarcode(@PathVariable("barcode") String barcode) {
        var boletoDTO = boletoService.findBoletoByBarcode(barcode);
        return ResponseEntity.ok(boletoDTO);
    }

    @PostMapping
    public ResponseEntity<BoletoDTO> save(@Valid @RequestBody BoletoRequestDTO boletoRequestDTO) {
        var boleto = boletoService.save(boletoRequestDTO.getBarcode());
        return new ResponseEntity<>(boleto, HttpStatus.CREATED);
    }
}
