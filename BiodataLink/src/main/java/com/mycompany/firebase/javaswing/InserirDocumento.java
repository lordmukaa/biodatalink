package com.mycompany.firebase.javaswing;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class InserirDocumento {

    private static void ensureCollectionExists(Firestore db, String collectionName) throws InterruptedException, ExecutionException {
        ApiFuture<DocumentSnapshot> future = db.collection(collectionName).document("dummyDoc").get();
        DocumentSnapshot document = future.get();

        if (!document.exists()) {
            Map<String, Object> data = new HashMap<>();
            data.put("dummyField", "dummyValue");
            db.collection(collectionName).document("dummyDoc").set(data).get();
        }
    }
    //Chama a conexao firebae, pega o banco de dads
    public static void InsertFromCsv() throws IOException, ExecutionException, InterruptedException {
        Conexao.initFirebase();
        Firestore db = FirestoreClient.getFirestore();

//passa o nome da coleçao especies
        ensureCollectionExists(db, "Especies");

        try { //passar parametros, formataçao defalt
            FileReader file = new FileReader("C:\\Users\\ninja\\Documents\\planilha.csv");
            CSVParser csvParser = CSVFormat.DEFAULT.withDelimiter(';').withSkipHeaderRecord().parse(file);

            for (CSVRecord record : csvParser) {
                if (record.size() < 14) { // Verifica se o registro tem o número esperado de colunas
                    System.err.println("Registro incompleto - Número de colunas: " + record.size());
                    System.err.println("Conteúdo do registro: " + record);
                    continue;
                }

                // Ler os dados  registro
                String faunaFlora = record.get(0);
                String grupo = record.get(1);
                String familia = record.get(2);
                String especie = record.get(3);
                String nomeComum = record.get(4);
                String categoriaAmeaca = record.get(5);
                String siglaAmeaca = record.get(6);
                String bioma = record.get(7);
                String principaisAmeacas = record.get(8);
                String presencaAreasProtegidas = record.get(9);
                String planoAcaoNacional = record.get(10);
                String ordenamentoPesqueiro = record.get(11);
                String nivelProtecaoEstrategia = record.get(12).trim();
                String especieExclusivaBrasil = record.get(13);
                String estadosOcorrencia = record.get(14);

                // Montar o documento espécie, mapear o banco
                Map<String, Object> especieData = new HashMap<>();
                especieData.put("FaunaFlora", faunaFlora);
                especieData.put("Grupo", grupo);
                especieData.put("Familia", familia);
                especieData.put("Especie", especie);
                especieData.put("NomeComum", nomeComum);
                especieData.put("CategoriaAmeaca", categoriaAmeaca);
                especieData.put("SiglaAmeaca", siglaAmeaca);
                especieData.put("Bioma", bioma);
                especieData.put("PrincipaisAmeacas", principaisAmeacas);
                especieData.put("PresencaAreasProtegidas", presencaAreasProtegidas);
                especieData.put("PlanoAcaoNacional", planoAcaoNacional);
                especieData.put("OrdenamentoPesqueiro", ordenamentoPesqueiro);
                especieData.put("NivelProtecaoEstrategia", nivelProtecaoEstrategia);
                especieData.put("EspecieExclusivaBrasil", especieExclusivaBrasil);
                especieData.put("EstadosOcorrencia", Arrays.asList(estadosOcorrencia.split(","))); //lista de conteudo separados por virgula

                // Inserir espécie na coleção
                ApiFuture<DocumentReference> futureEspecie = db.collection("Especies").add(especieData);
                DocumentReference especieRef = futureEspecie.get();

                System.out.println("Espécie inserida com ID: " + especieRef.getId());
            }

            csvParser.close();
            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

