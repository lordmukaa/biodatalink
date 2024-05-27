package com.mycompany.firebase.javaswing;

import java.util.ArrayList;

public class DocumentoEspecie {
    private String NivelProtecaoEstrategia, NomeComum, PresencaAreasProtegidas, OrdenamentoPesqueiro, FaunaFlora, Familia, CategoriaAmeaca, SiglaAmeaca, Bioma, PrincipaisAmeacas, EspecieExclusivaBrasil, Grupo, PlanoAcaoNacional, Especie;
    private ArrayList<String> EstadosOcorrencia;

    public String getNivelProtecaoEstrategia() {
        return NivelProtecaoEstrategia;
    }

    public void setNivelProtecaoEstrategia(String nivelProtecaoEstrategia) {
        NivelProtecaoEstrategia = nivelProtecaoEstrategia;
    }

    public String getNomeComum() {
        return NomeComum;
    }

    public void setNomeComum(String nomeComum) {
        NomeComum = nomeComum;
    }

    public String getPresencaAreasProtegidas() {
        return PresencaAreasProtegidas;
    }

    public void setPresencaAreasProtegidas(String presencaAreasProtegidas) {
        PresencaAreasProtegidas = presencaAreasProtegidas;
    }

    public String getOrdenamentoPesqueiro() {
        return OrdenamentoPesqueiro;
    }

    public void setOrdenamentoPesqueiro(String ordenamentoPesqueiro) {
        OrdenamentoPesqueiro = ordenamentoPesqueiro;
    }

    public String getFaunaFlora() {
        return FaunaFlora;
    }

    public void setFaunaFlora(String faunaFlora) {
        FaunaFlora = faunaFlora;
    }

    public String getFamilia() {
        return Familia;
    }

    public void setFamilia(String familia) {
        Familia = familia;
    }

    public String getCategoriaAmeaca() {
        return CategoriaAmeaca;
    }

    public void setCategoriaAmeaca(String categoriaAmeaca) {
        CategoriaAmeaca = categoriaAmeaca;
    }

    public String getSiglaAmeaca() {
        return SiglaAmeaca;
    }

    public void setSiglaAmeaca(String siglaAmeaca) {
        SiglaAmeaca = siglaAmeaca;
    }

    public String getBioma() {
        return Bioma;
    }

    public void setBioma(String bioma) {
        Bioma = bioma;
    }

    public String getPrincipaisAmeacas() {
        return PrincipaisAmeacas;
    }

    public void setPrincipaisAmeacas(String principaisAmeacas) {
        PrincipaisAmeacas = principaisAmeacas;
    }

    public ArrayList<String> getEstadosOcorrencia() {
        return EstadosOcorrencia;
    }

    public void setEstadosOcorrencia(ArrayList<String> estadosOcorrencia) {
        EstadosOcorrencia = estadosOcorrencia;
    }

    public String getEspecieExclusivaBrasil() {
        return EspecieExclusivaBrasil;
    }

    public void setEspecieExclusivaBrasil(String especieExclusivaBrasil) {
        EspecieExclusivaBrasil = especieExclusivaBrasil;
    }

    public String getGrupo() {
        return Grupo;
    }

    public void setGrupo(String grupo) {
        Grupo = grupo;
    }

    public String getPlanoAcaoNacional() {
        return PlanoAcaoNacional;
    }

    public void setPlanoAcaoNacional(String planoAcaoNacional) {
        PlanoAcaoNacional = planoAcaoNacional;
    }

    public String getEspecie() {
        return Especie;
    }

    public void setEspecie(String especie) {
        Especie = especie;
    }

    public boolean filtro(String selectedFaunaFlora, String selectedFamilia, String selectedGrupo, String especieText, String nomeComumText, String categoriaAmeacaText, String principaisAmeacaTxt, String biomaText, String estadoOcorrenciaTxt) {
        if (!selectedFaunaFlora.equals(getFaunaFlora())) { //recebe como parametro
            return false; //ele pode pesquisar com os campos limpos com o uso da "!", o mesmo acontece para todos
        }
        if (!selectedFamilia.equals(getFamilia())) {
            return false;
        }
        if (!selectedGrupo.equals(getGrupo())) {
            return false;
        }
        if (!especieText.isEmpty() && !getEspecie().contains(especieText)) {
            return false;
        }
        if (!nomeComumText.isEmpty() && !getNomeComum().contains(nomeComumText)) {
            return false;
        }
        if (!categoriaAmeacaText.isEmpty() && !getCategoriaAmeaca().contains(categoriaAmeacaText)) {
            return false;
        }
        if (!principaisAmeacaTxt.isEmpty() && !getPrincipaisAmeacas().contains(principaisAmeacaTxt)) {
            return false;
        }
        if (!biomaText.isEmpty() && !getBioma().contains(biomaText)) {
            return false;
        }
        if (!estadoOcorrenciaTxt.isEmpty() && !getEstadosOcorrencia().contains(estadoOcorrenciaTxt)) {
            return false;
        }
        return true;
    }
}
