package br.com.daniel.CarPriceRequest.Principal;

import br.com.daniel.CarPriceRequest.model.*;
import br.com.daniel.CarPriceRequest.service.ConsumoApi;
import br.com.daniel.CarPriceRequest.service.ConverterDados;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private final Scanner leitura = new Scanner(System.in);
    private final ConsumoApi consumoApi = new ConsumoApi();
    private final ConverterDados conversor = new ConverterDados();
    private final String ENDERECO = "https://parallelum.com.br/fipe/api/v1";

    public void menu () {
        String buscaAutomovel = null;
        while (buscaAutomovel == null) {
            exibirMensagem();
            buscaAutomovel = leitura.nextLine();
            buscaAutomovel = completaPalavra(buscaAutomovel);
        }

        List<Dados> listaDadosAutomoveis = conversor.obterLista(consumoApi
                .obterDados(ENDERECO +"/" +
                buscaAutomovel + "/marcas"), Dados.class);
        var listaAutomoveis = listaDadosAutomoveis.stream()
                .map(d -> new DadosEndity(d.codigo(), d.nome()));
        listaAutomoveis.forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta: ");
        var buscaMarca = leitura.nextLine();

        var modelosLista = conversor.obterDados(consumoApi
                .obterDados(ENDERECO +"/" +
                buscaAutomovel + "/marcas/" +
                buscaMarca +
                "/modelos"), Modelos.class);
        var modelos = modelosLista.modelos()
                .stream().map(d -> new DadosEndity(d.codigo(), d.nome())).toList();
        modelos.forEach(System.out::println);

        System.out.println("Digite um trecho do nome do veículo para consulta:");
        var trecho = leitura.nextLine();
        var modeloFiltrado = modelos.stream()
                .filter(d -> d.getNome().toLowerCase()
                        .contains(trecho.toLowerCase()));
        modeloFiltrado.forEach(System.out::println);

        System.out.println("Digite o código do modelo para consultar o valor:");
        var codigoModel = leitura.nextLine();

        List<Dados> anosLista = conversor.obterLista(consumoApi
                .obterDados(ENDERECO +"/" +
                buscaAutomovel + "/marcas/" +
                buscaMarca +
                "/modelos/" + codigoModel
                + "/anos"), Dados.class);

        System.out.println("Todos os veículos por ano:");
        for (int i=0; i < anosLista.size(); i++) {
            DadosPreco preco = conversor.obterDados(consumoApi
                    .obterDados(ENDERECO +"/" +
                    buscaAutomovel + "/marcas/" +
                    buscaMarca +
                    "/modelos/" + codigoModel
                    + "/anos/" + anosLista.get(i).codigo()), DadosPreco.class);
            System.out.println(new DadosPrecoEndity(preco.valor(), preco.marca(),
                    preco.modelo(), preco.anoModelo(), preco.combustivel()));
        }
    }

    private void exibirMensagem() {
        var msg = """
                ***Verificar Preços tabela FIPE***
                
                Carro
                Moto
                Caminhão
                
                Digite sua consulta:
                """;
        System.out.println(msg);
    }

    private String completaPalavra (String palavraLida) {
        if (palavraLida.toLowerCase().contains("car")) {
            return "carros";
        } else if (palavraLida.toLowerCase().contains("mot")) {
            return "motos";
        } else if (palavraLida.toLowerCase().contains("cam")) {
            return "caminhoes";
        }
        System.out.println("Palavra incorreta!");
        return null;
    }
}
