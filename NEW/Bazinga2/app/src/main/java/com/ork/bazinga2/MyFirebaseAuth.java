package com.ork.bazinga2;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MyFirebaseAuth {

    // [END declare_auth]
    public static Task<Void> signOut(FirebaseFirestore db, FirebaseAuth mAuth, GoogleSignInClient mGoogleSignInClient) {

        Map<String, Object> m = new HashMap<>();
        m.put("logout",new Date().getTime());
        db.collection("users")
                .document(mAuth.getUid())
                .update(m);
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        return mGoogleSignInClient.signOut();
    }
}
