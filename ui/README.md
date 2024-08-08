# Bedrock UI

Reusable Compose views and themes for use in Sample Apps and Developer UIs

Components
- [Tabs](#bedrock-tabs)
- [Favorite](#bedrock-favorite)
- [Icon Toggle](#bedrock-icon-toggle)
- [Theme](#bedrock-theme)
- [observeAsState extension](#observeasstate-lifecycle-extension)


### Bedrock Tabs
```kotlin
BedrockTabs(
    modifier = Modifier.fillMaxSize(),
    tabItems = listOf(
        BedrockTabItem({ Text("Home") }, { DemoAppScreen("Hello World") }),
        BedrockTabItem({ Text("Favorites") }, { FavoritesTabContent() }),
    ),
)
```

### Bedrock Favorite
```kotlin
BedrockFavorite(isFavorited = false) {
    // Handle State Change
}
```

### Bedrock Icon Toggle
Can use any two filled/empty icon pairs
```kotlin
BedrockIconToggle(
    checkedIcon = R.drawable.kds_icons_flag_solid,
    uncheckedIcon = R.drawable.kds_icons_flag,
) {
    // Handle State Change
}
```

### Bedrock Theme
Provides colors, dimensions, and typography components, in addition to being a custom implementation of the Material Theme
```kotlin
BedrockTheme {
    // compose content
}
```

### observeAsState Lifecycle Extension
Creates an observer to reflect the current UI State

```kotlin
val lifecycleState = lifecycleOwner.lifecycle.observeAsState()

if (lifecycleState.value == Lifecycle.Event.ON_RESUME) {
    // Do onResume stuff
}
```
