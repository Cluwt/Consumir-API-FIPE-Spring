/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CesarRodrigues.ConsultaFIPE;
import com.CesarRodrigues.ConsultaFIPE.CarroModel.Ano;
import com.CesarRodrigues.ConsultaFIPE.CarroModel.Marca;
import com.CesarRodrigues.ConsultaFIPE.CarroModel.Modelo;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FipeClient {

    private static final String BASE_URL = "https://parallelum.com.br/fipe/api/v1/carros";

    private final RestTemplate restTemplate;

    public FipeClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Marca[] listarMarcas() {
        return restTemplate.getForObject(BASE_URL + "/marcas", Marca[].class);
    }

    public Modelo[] listarModelos(String codigoMarca) {
        return restTemplate.getForObject(BASE_URL + "/marcas/{codigo}/modelos", Modelo[].class, codigoMarca);
    }

    public Ano[] listarAnos(String codigoMarca, String codigoModelo) {
        return restTemplate.getForObject(BASE_URL + "/marcas/{codigoMarca}/modelos/{codigoModelo}/anos", Ano[].class, codigoMarca, codigoModelo);
    }

    public String consultarPreco(String codigoMarca, String codigoModelo, String codigoAno) {
        String url = String.format("%s/marcas/%s/modelos/%s/anos/%s", BASE_URL, codigoMarca, codigoModelo, codigoAno);
        FipeResponse resposta = restTemplate.getForObject(url, FipeResponse.class);
        if (resposta != null) {
            return resposta.getValor();
        } else {
            return "Não foi possível obter o preço do carro.";
        }
    }
}
