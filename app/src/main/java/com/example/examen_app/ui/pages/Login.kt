package com.example.examen_app.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign 
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.examen_app.R
import androidx.compose.foundation.shape.RoundedCornerShape


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onNavigateToSignup: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    fun validateEmail(email: String): Boolean {
        return if (email.isEmpty()) {
            emailError = "El email es requerido"
            false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = "Formato de email inválido"
            false
        } else {
            emailError = ""
            true
        }
    }

    fun validatePassword(password: String): Boolean {
        return if (password.isEmpty()) {
            passwordError = "La contraseña es requerida"
            false
        } else if (password.length < 6) {
            passwordError = "La contraseña debe tener al menos 6 caracteres"
            false
        } else {
            passwordError = ""
            true
        }
    }

    fun handleLogin() {
        val isEmailValid = validateEmail(email)
        val isPasswordValid = validatePassword(password)

        if (isEmailValid && isPasswordValid) {
            
            emailError = ""
            passwordError = ""
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título
        Text(
            text = "Login",
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Welcome back!",
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Campo Email
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = ""
            },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            isError = emailError.isNotEmpty(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.Gray,
                errorBorderColor = MaterialTheme.colorScheme.error,
                
                errorSupportingTextColor = MaterialTheme.colorScheme.error
            ),
           
            supportingText = {
                if (emailError.isNotEmpty()) {
                    Text(
                        text = emailError,
                        color = MaterialTheme.colorScheme.error, 
                        fontSize = 12.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End 
                    )
                }
            }
           
        )

      

        Spacer(modifier = Modifier.height(16.dp))

        // Campo Password
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = ""
            },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            isError = passwordError.isNotEmpty(),
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.Gray,
                errorBorderColor = MaterialTheme.colorScheme.error,
                
                errorSupportingTextColor = MaterialTheme.colorScheme.error
            ),
            
            supportingText = {
                if (passwordError.isNotEmpty()) {
                    Text(
                        text = passwordError,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End 
                    )
                }
            }
            
        )

       

        Spacer(modifier = Modifier.height(8.dp))

        // Forgot Password
        TextButton(
            onClick = { /* Acción de recuperar contraseña */ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Forgot Password?",
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Botón Login
        Button(
            onClick = { handleLogin() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Login",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Don't have an account
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don't have an account?  ",
                color = Color.Black
            )
            TextButton(onClick = onNavigateToSignup) {
                Text(
                    text = "Signup",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Divider Or
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(modifier = Modifier.weight(1f))
            Text(
                text = "  Or  ",
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Divider(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Login with Facebook
        OutlinedButton(
            onClick = { /* Acción de login con Facebook */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color(0xFFF5F5F5)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.face),
                contentDescription = "Facebook",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Login with Facebook",
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Login with Google
        OutlinedButton(
            onClick = { /* Acción de login con Google */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.google_logo),
                contentDescription = "Google",
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Login with Google",
                color = Color.Black
            )
        }
    }
}
