package com.mycompany.firebase.javaswing;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Operacoes {
    private final JTable table;
    private final JComboBox<String> jComboBox1, jComboBox2, jComboBox3;
    private final JTextField jTextField1, jTextField2, jTextField3, jTextField4, jTextField5, jTextField6;

    public Operacoes(JTable table, JComboBox<String> jComboBox1, JComboBox<String> jComboBox2, JComboBox<String> jComboBox3, JTextField jTextField1, JTextField jTextField2, JTextField jTextField3, JTextField jTextField4, JTextField jTextField5, JTextField jTextField6) {
        this.table = table;
        this.jComboBox1 = jComboBox1;
        this.jComboBox2 = jComboBox2;
        this.jComboBox3 = jComboBox3;
        this.jTextField1 = jTextField1;
        this.jTextField2 = jTextField2;
        this.jTextField3 = jTextField3;
        this.jTextField4 = jTextField4;
        this.jTextField5 = jTextField5;
        this.jTextField6 = jTextField6;
    }
    //pesquisar chama orperaçoes
    public void pesquisar() throws ExecutionException, InterruptedException {
        String faunaFlora = (String) jComboBox1.getSelectedItem();
        String familia = (String) jComboBox2.getSelectedItem();
        String grupo = (String) jComboBox3.getSelectedItem();
        String especie = jTextField1.getText();
        String nomeComum = jTextField4.getText();
        String categoriaAmeaca = jTextField2.getText();
        String principaisAmeaca = jTextField5.getText();
        String bioma = jTextField3.getText();
        String estadoOcorrencia = jTextField6.getText();
        Firestore db = FirestoreClient.getFirestore();
        Query especiesRef = db.collection("Especies"); //responsalvelk pela pesquisa
        ApiFuture<QuerySnapshot> querySnapshot = especiesRef.get();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (DocumentSnapshot documento : querySnapshot.get().getDocuments()) { //traz todos os dados um de cada vez
            DocumentoEspecie docDocumentoEspecie = documento.toObject(DocumentoEspecie.class); //pega o documento e tranforma em objeto "DocumentoEspecie.class"
            if(docDocumentoEspecie.filtro(faunaFlora, familia, grupo, especie, nomeComum, categoriaAmeaca ,principaisAmeaca, bioma, estadoOcorrencia)){
                model.addRow(new Object[]{ //formato que vem da tabela do front
                        docDocumentoEspecie.getFaunaFlora(), docDocumentoEspecie.getGrupo(), docDocumentoEspecie.getFamilia(), docDocumentoEspecie.getEspecie(), docDocumentoEspecie.getNomeComum(), docDocumentoEspecie.getCategoriaAmeaca(), docDocumentoEspecie.getSiglaAmeaca(), docDocumentoEspecie.getBioma() , docDocumentoEspecie.getPrincipaisAmeacas(), docDocumentoEspecie.getPresencaAreasProtegidas(), docDocumentoEspecie.getPlanoAcaoNacional() , docDocumentoEspecie.getOrdenamentoPesqueiro(), docDocumentoEspecie.getNivelProtecaoEstrategia(), docDocumentoEspecie.getEspecieExclusivaBrasil(), docDocumentoEspecie.getEstadosOcorrencia()
                }); //mesma ordem da tabela
            }
            table.setModel(model); //seta o modelo, ele entrega, atualiza e coloca no front
        }
    }

    public List<DocumentoEspecie> sincronizar() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore(); //traz tudo q tem no banco
        ApiFuture<QuerySnapshot> querySnapshot = db.collection("Especies").get();
        List<DocumentoEspecie> especiesList = new ArrayList<>();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            DocumentoEspecie documentoEspecie = document.toObject(DocumentoEspecie.class);
            especiesList.add(documentoEspecie); //mesma coisa so quem sem o filtro e retorna a lista
        }
        return especiesList; //retorna a lista pro menu
    }

    public void atualizaTabela(List<DocumentoEspecie> data) { //entrega os valores na tabela
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (DocumentoEspecie item : data) {
            model.addRow(new Object[]{
                    item.getFaunaFlora(), item.getGrupo(), item.getFamilia(), item.getEspecie(), item.getNomeComum(), item.getCategoriaAmeaca(), item.getSiglaAmeaca(), item.getBioma(), item.getPrincipaisAmeacas(), item.getPresencaAreasProtegidas(), item.getPlanoAcaoNacional(), item.getOrdenamentoPesqueiro(), item.getNivelProtecaoEstrategia(), item.getEspecieExclusivaBrasil(), item.getEstadosOcorrencia()
            });
        }
    }

    public void popularComboBox() throws IOException { //responsavel pela conexao
        Conexao.initFirebase(); //primeiro a ser clicado para atualizar e conectar
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("Especies").get();
        try {
            QuerySnapshot querySnapshot = future.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments(); //puxa todos os dados do banco e armazena nas listas
            List<String> faunaFloraList = new ArrayList<>();
            List<String> familiaList = new ArrayList<>();
            List<String> grupoList = new ArrayList<>();
            for (QueryDocumentSnapshot document : documents) { //ve quais os nomes iguais e filtra para aparecer um
                Map<String, Object> data = document.getData();
                String faunaFlora = (String) data.get("FaunaFlora");
                String familia = (String) data.get("Familia");
                String grupo = (String) data.get("Grupo");
                if (!faunaFloraList.contains(faunaFlora)) {
                    faunaFloraList.add(faunaFlora);
                }
                if (!familiaList.contains(familia)) {
                    familiaList.add(familia);
                }
                if (!grupoList.contains(grupo)) {
                    grupoList.add(grupo);
                }
            } //passa a lista para os campos
            jComboBox1.setModel(new DefaultComboBoxModel<>(faunaFloraList.toArray(new String[0])));
            jComboBox2.setModel(new DefaultComboBoxModel<>(familiaList.toArray(new String[0])));
            jComboBox3.setModel(new DefaultComboBoxModel<>(grupoList.toArray(new String[0])));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
