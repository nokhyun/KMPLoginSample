import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.aakira.napier.Napier
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.launch
import login.google.GoogleAuthProvider
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    val platformName = platformName()
    MaterialTheme {
        Napier.e("test")
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { platformName }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }

            when (platformName()) {
                "Android" -> {
                    GoogleButtonUiContainer(
                        onGoogleSignInResult = {
                            Napier.e("result: ${it?.displayName}")
                        }
                    ) {
                        Button(
                            onClick = { this.onClick() }
                        ) {
                            Text("Google")
                        }
                    }
                }

                else -> {}
            }
        }
    }
}

interface GoogleButtonUiContainerScope {
    fun onClick()
}

@Composable
fun GoogleButtonUiContainer(
    modifier: Modifier = Modifier,
    onGoogleSignInResult: (GoogleUser?) -> Unit,
    content: @Composable GoogleButtonUiContainerScope.() -> Unit
) {
    val googleAuthProvider = koinInject<GoogleAuthProvider>()
    val googleAuthUiProvider = googleAuthProvider.getUiProvider()
    val coroutineScope = rememberCoroutineScope()
    val uiContainerScope = remember {
        object : GoogleButtonUiContainerScope {
            override fun onClick() {
                coroutineScope.launch {
                    val googleUser = googleAuthUiProvider.signIn()
                    onGoogleSignInResult(googleUser)
                }
            }
        }
    }
    Surface(
        modifier = modifier,
        content = { uiContainerScope.content() }
    )
}