# Installation

Add it in your `settings.gradle.kts` at the end of repositories:
   ```
dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		mavenCentral()
		maven { url = uri("https://jitpack.io") }
	}
}
```
Then, add the dependency:
```
dependencies {
    implementation("com.github.LKachanovsky:ideal_size:1.0.1")
}
```
# Usage

```
@Composable
fun SampleUsage(
    modifier: Modifier = Modifier
) {
    var doShowDialog by rememberSaveable { mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Button(onClick = { doShowDialog = true }) {
            Text("Find your perfect fit")
        }
    }

    if (doShowDialog) {
        IdealSizeDialog(
            onDismiss = { doShowDialog = false },
            onOkClicked = { doShowDialog = false },
        )
    }
}
```
