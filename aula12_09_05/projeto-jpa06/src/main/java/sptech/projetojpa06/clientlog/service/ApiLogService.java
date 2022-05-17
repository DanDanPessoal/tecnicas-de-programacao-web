package sptech.projetojpa06.clientlog.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sptech.projetojpa06.clientlog.resposta.Log;

@FeignClient(name = "apiLog", url = " https://5f861cfdc8a16a0016e6aacd.mockapi.io/bandtec-api/logs")
public interface ApiLogService {

    @PostMapping
    void postLog(@RequestBody Log log);

}
