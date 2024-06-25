package com.example.tuesday.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tuesday.MainActivity
import com.example.tuesday.R
import com.example.tuesday.databinding.ActivityLoginPageBinding
import com.example.tuesday.databinding.ActivitySignInBinding
import com.example.tuesday.fragment.UserEventFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class login_page : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var binding: ActivityLoginPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityLoginPageBinding.inflate(layoutInflater)
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
        Log.d("checksign",signInIntent.toString())
        launcher.launch(signInIntent)


    }
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            Log.d("checkre", "결과확인 1")
            if (result.resultCode == Activity.RESULT_OK) {
                Log.d("checkre", "결과확인 2")
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleResults(task)
            } else {
                Log.d("checkre", "결과확인 실패: ${result.resultCode}")
                if (result.data != null) {
                    Log.d("checkre", "data: ${result.data}")
                }
            }
        }
    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account: GoogleSignInAccount? = task.result

            Log.d("checkre","결과확인")
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
                val intent: Intent = Intent(this, SelectFavorActivity::class.java)//예시로 해놓은것이니 고쳐야함
                //어디로 보낼지 저ㄱ는코드
                //이메일을 보내고 있는데 사용자 이름 받아서 보내는것도 하자
                intent.putExtra("email", account.email)
                intent.putExtra("account",account)
                intent.putExtra("name", account.displayName) // 사용자 이름 추가
                Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show()
                finish()
                startActivity(intent)
            } else {
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}