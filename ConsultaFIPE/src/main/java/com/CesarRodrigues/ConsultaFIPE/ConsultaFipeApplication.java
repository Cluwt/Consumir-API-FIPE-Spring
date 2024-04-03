package com.CesarRodrigues.ConsultaFIPE;

import com.CesarRodrigues.ConsultaFIPE.CarroModel.Ano;
import com.CesarRodrigues.ConsultaFIPE.CarroModel.Marca;
import com.CesarRodrigues.ConsultaFIPE.CarroModel.Modelo;
import java.util.Scanner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsultaFipeApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FipeClient fipeClient = new FipeClient(new RestTemplate());

        System.out.println("Bem-vindo à consulta de preços da FIPE!");
        System.out.println("---------------------------------------");

        // Listar marcas
        System.out.println("Marcas disponíveis:");
        Marca[] marcas = fipeClient.listarMarcas();
        for (Marca marca : marcas) {
            System.out.println(marca.getCodigo() + ": " + marca.getNome());
        }

        // Selecionar marca
        System.out.println("Digite o código da marca desejada:");
        String codigoMarca = scanner.nextLine();

        // Listar modelos da marca selecionada
        System.out.println("Modelos disponíveis:");
        Modelo[] modelos = fipeClient.listarModelos(codigoMarca);
        for (Modelo modelo : modelos) {
            System.out.println(modelo.getCodigo() + ": " + modelo.getNome());
        }

        // Selecionar modelo
        System.out.println("Digite o código do modelo desejado:");
        String codigoModelo = scanner.nextLine();

        // Listar anos do modelo selecionado
        System.out.println("Anos disponíveis:");
        Ano[] anos = fipeClient.listarAnos(codigoMarca, codigoModelo);
        for (Ano ano : anos) {
            System.out.println(ano.getCodigo() + ": " + ano.getNome());
        }

        // Selecionar ano
        System.out.println("Digite o código do ano desejado:");
        String codigoAno = scanner.nextLine();

        // Consultar preço
        String preco = fipeClient.consultarPreco(codigoMarca, codigoModelo, codigoAno);
        System.out.println("Preço do carro: " + preco);
    }

    
    }

