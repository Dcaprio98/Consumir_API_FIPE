package br.com.daniel.CarPriceRequest.model;

public class DadosPrecoEndity {
    private String valor;
    private String marca;
    private String modelo;
    private Integer anoModelo;
    private String combustivel;

    public DadosPrecoEndity(String valor, String marca,
                            String modelo, Integer anoModelo, String combustivel) {
        this.valor = valor;
        this.marca = marca;
        this.modelo = modelo;
        this.anoModelo = anoModelo;
        this.combustivel = combustivel;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(Integer anoModelo) {
        this.anoModelo = anoModelo;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    @Override
    public String toString() {
        return  "Valor='" + valor + '\'' +
                "; Marca='" + marca + '\'' +
                "; Modelo='" + modelo + '\'' +
                "; Ano do modelo=" + anoModelo +
                "; Combustivel='" + combustivel + ";";
    }
}
