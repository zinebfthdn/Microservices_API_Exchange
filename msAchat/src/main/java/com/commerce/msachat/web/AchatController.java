package com.commerce.msachat.web;

import com.commerce.msachat.dto.AchatRequestDTO;
import com.commerce.msachat.dto.AchatResponseDTO;
import com.commerce.msachat.services.AchatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/achats")
@RequiredArgsConstructor
public class AchatController {

    private final AchatService achatService;

    @PostMapping
    public AchatResponseDTO createAchat(@RequestBody AchatRequestDTO requestDTO) {
        return achatService.addAchat(requestDTO);
    }

    @GetMapping
    public List<AchatResponseDTO> getAllAchats() {
        return achatService.getAllAchats();
    }
}
