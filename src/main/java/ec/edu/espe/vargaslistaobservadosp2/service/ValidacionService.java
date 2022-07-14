package ec.edu.espe.vargaslistaobservadosp2.service;

import ec.edu.espe.vargaslistaobservadosp2.dao.NarcotraficanteRepository;
import ec.edu.espe.vargaslistaobservadosp2.dto.ClienteDTO;
import ec.edu.espe.vargaslistaobservadosp2.model.Narcotraficantes;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ValidacionService {
    private static final String BASE_URL = "http://localhost:8081/transaccion/validarCliente";

    private final RestTemplate restTemplate;

    private final NarcotraficanteRepository narcotraficanteRepository;


    public ValidacionService(NarcotraficanteRepository narcotraficante){
        this.narcotraficanteRepository = narcotraficante;
        this.restTemplate = new RestTemplate(getClientHttpRequestFactory());
    }

    public String validarNacrotraficante(){
        ResponseEntity<ClienteDTO>response = this.restTemplate.getForEntity(BASE_URL, ClienteDTO.class);
        List<Narcotraficantes> list = this.narcotraficanteRepository.findAll();
        ClienteDTO clienteComprobar = response.getBody();
        for(Narcotraficantes narco : list){
            if(clienteComprobar.getNombreCompleto().equals(narco.getNombreCompleto())){
                return "El cliente está en la lista de Observados";
            }
        }
        return "El cliente no se encuentra en la lista de observados";
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        int connectTimeout = 5000;
        int readTimeout = 5000;
        clientHttpRequestFactory.setConnectTimeout(connectTimeout);
        clientHttpRequestFactory.setReadTimeout(readTimeout);
        return clientHttpRequestFactory;
    }
}
