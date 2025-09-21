package br.com.sprint03.investidorapi.controller;

import br.com.sprint03.investidorapi.dto.CreatePositionDto;
import br.com.sprint03.investidorapi.dto.PositionDto;
import br.com.sprint03.investidorapi.service.PositionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/positions")
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }



    @PostMapping
    public ResponseEntity<PositionDto> create(@RequestBody @Valid CreatePositionDto createPositionDto) {
        PositionDto positionDto = positionService.create(createPositionDto);
        return ResponseEntity.ok(positionDto);
    }

    @GetMapping
    public ResponseEntity<List<PositionDto>> getByAccountId(@RequestParam("accountId") UUID accountId) {
        List<PositionDto> positions = positionService.findByAccountId(accountId);
        return ResponseEntity.ok(positions);
    }
}