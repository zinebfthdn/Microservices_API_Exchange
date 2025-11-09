package com.commerce.msachat.services;

import com.commerce.msachat.dto.AchatRequestDTO;
import com.commerce.msachat.dto.AchatResponseDTO;

import java.util.List;

public interface AchatService {
    AchatResponseDTO addAchat(AchatRequestDTO requestDTO);
    List<AchatResponseDTO> getAllAchats();
}
