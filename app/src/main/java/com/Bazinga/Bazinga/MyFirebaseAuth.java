package com.Bazinga.Bazinga;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MyFirebaseAuth {

    // [END declare_auth]
    public static Task<Void> signOut(FirebaseAuth mAuth, GoogleSignInClient mGoogleSignInClient) {
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
       return mGoogleSignInClient.signOut();
    }

    public static Task<Void> revokeAccess(FirebaseAuth mAuth, GoogleSignInClient mGoogleSignInClient) {
        // Firebase sign out
        mAuth.signOut();

        // Google revoke access
        return mGoogleSignInClient.revokeAccess();
    }
}
