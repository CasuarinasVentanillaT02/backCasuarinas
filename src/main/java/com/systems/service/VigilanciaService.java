package com.systems.service;

import com.systems.dto.VigCocheraHabiDTO;


public interface VigilanciaService {
    VigCocheraHabiDTO fViewVigHabiVehiCocheraXPlaca(String secretKey, String asDePlaca);
}
