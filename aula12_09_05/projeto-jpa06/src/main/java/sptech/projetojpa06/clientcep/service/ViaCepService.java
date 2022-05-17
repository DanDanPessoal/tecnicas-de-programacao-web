package sptech.projetojpa06.clientcep.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sptech.projetojpa06.clientcep.resposta.Cep;

@FeignClient(value = "viacep", url = "https://viacep.com.br/ws/")
public interface ViaCepService {

    @GetMapping("{cep}/json/")
    Cep getCep(@PathVariable String cep);

}
