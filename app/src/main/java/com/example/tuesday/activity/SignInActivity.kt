package com.example.tuesday.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.tuesday.R
import com.example.tuesday.databinding.ActivitySignInBinding
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class SignInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        binding=ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        binding.signinBtn.setOnClickListener {
            signInGoogle()
        }

    }
      private fun signInGoogle() {
             val signInIntent = googleSignInClient.signInIntent
             launcher.launch(signInIntent)

         }

         private val launcher =
             registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                 if (result.resultCode == Activity.RESULT_OK) {
                     val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                     handleResults(task)
                 }
             }

         private fun handleResults(task: Task<GoogleSignInAccount>) {
             if (task.isSuccessful) {
                 val account: GoogleSignInAccount? = task.result
                 if (account != null) {
                     updateUI(account)
                 }
             } else {
                 Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
             }
         }

         private fun updateUI(account: GoogleSignInAccount) {
             val credential = GoogleAuthProvider.getCredential(account.idToken, null)
             auth.signInWithCredential(credential).addOnCompleteListener {
                 if (it.isSuccessful) {
                     val intent: Intent = Intent(this, SignInActivity::class.java)//예시로 해놓은것이니 고쳐야함
                     //어디로 보낼지 저ㄱ는코드
                     intent.putExtra("email", account.email)
                     Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show()
                     startActivity(intent)
                 } else {
                     Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                 }
             }
         }


}