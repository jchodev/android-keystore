package com.jerry.keystoreproject

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jerry.keystoreproject.manager.CryptoManager
import com.jerry.keystoreproject.manager.CryptoManager2
import com.jerry.keystoreproject.ui.theme.KeystoreprojectTheme
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val file = File(filesDir, "my_secret.txt")
        if (!file.exists()) {
            file.createNewFile()
        }

        val cryptoManager = CryptoManager()
        val cryptoManager2 = CryptoManager2()

        setContent {
            KeystoreprojectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var messageToEncrypt by remember {
                        mutableStateOf("")
                    }
                    var messageToDecrypt by remember {
                        mutableStateOf("")
                    }
                    Column(
                        modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(32.dp)
                    ) {
                        TextField(
                            value = messageToEncrypt,
                            onValueChange = { messageToEncrypt = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text(text = "Input string to Encrypt!") }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
//                        Row {
//                            Button(onClick = {
//                                val bytes = messageToEncrypt.encodeToByteArray()
//
//                                val fos = FileOutputStream(file)
//
//                                messageToDecrypt = cryptoManager.encrypt(
//                                    bytes = bytes,
//                                    outputStream = fos
//                                ).decodeToString()
//                            }) {
//                                Text(text = "Encrypt")
//                            }
//                            Spacer(modifier = Modifier.width(16.dp))
//                            Button(onClick = {
//                                messageToEncrypt = cryptoManager.decrypt(
//                                    inputStream = FileInputStream(file)
//                                ).decodeToString()
//                            }) {
//                                Text(text = "Decrypt From secret File!")
//                            }
//                        }
//                        Text(text = messageToDecrypt)
                        Row {
                            Button(onClick = {

//                                messageToDecrypt = cryptoManager2.encrypt(
//                                    messageToEncrypt
//                                ).decodeToString()
                                messageToDecrypt = cryptoManager2.encryptText(
                                    textToEncrypt = messageToEncrypt
                                )

                            }) {
                                Text(text = "Encrypt")
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Button(onClick = {
//                                val aaa =  cryptoManager2.decrypt(
//                                    messageToDecrypt
//                                )
//                                messageToEncrypt = aaa.decodeToString()
                                messageToEncrypt = cryptoManager2.decryptData(
                                    text = messageToDecrypt
                                )
                            }) {
                                Text(text = "Decrypt From secret File!")
                            }
                        }
                        Text(text = messageToDecrypt)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KeystoreprojectTheme {
        Greeting("Android")
    }
}