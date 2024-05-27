package com.mycompany.firebase.javaswing;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
// documentação firebase
// conexao por token, pego de la
public class Conexao {
    public static void initFirebase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("C:\\Users\\ninja\\Documents\\tokenlord.json");
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://Minecraft2.firebaseio.com/")
                .build();
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }

    public static Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }
}
