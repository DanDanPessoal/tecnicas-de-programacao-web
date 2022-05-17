package sptech.projetojpa06.clientraca.service;

import feign.Feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sptech.projetojpa06.clientraca.resposta.Raca;

@FeignClient(value = "apiRaca", url = "https://5f861cfdc8a16a0016e6aacd.mockapi.io/bandtec-api/racas/")
public interface ApiRacaService {

    @GetMapping("{idRaca}")
    Raca getRaca(@PathVariable String idRaca);

}
