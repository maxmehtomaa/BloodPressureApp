package com.example.bloodpressureexample;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;


public class DatabaseController {
    public static final String TAG = "DatabaseController";
    private static DatabaseController instance;
    private FirebaseFirestore db;

    public interface ResultListener {
        void result(BloodPressureResult result);
    }

    private DatabaseController() {
        db = FirebaseFirestore.getInstance();
    }

    public static DatabaseController getInstance() {
        if (instance == null) {
            instance = new DatabaseController();
        }
        return instance;
    }

    public void createResult(int sys, int dys, int pulse) {
        final CollectionReference collectionReference = db.collection("results");
        Map<String, Object> result = new HashMap<>();

        result.put("sys", sys);
        result.put("dys", dys);
        result.put("pulse", pulse);
        result.put("timestamp", FieldValue.serverTimestamp());

       db.collection("results").add(result).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "Result successfully created!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Failed to create a result");
            }
        });
    }

    public void getResultsASync(final ResultListener resultListener) {
        final CollectionReference collectionReference = db.collection("results");

        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        BloodPressureResult result = document.toObject(BloodPressureResult.class);

                        resultListener.result(result);
                    }
                } else {
                    Log.d(TAG, "Error on getting results: " + task.getException());
                }
            }
        });
    }
}
