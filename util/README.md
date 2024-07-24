# Bedrock Utilities

### Coroutine Helpers
#### Single Runner
Ensure that the block will only be executed after all previous work has completed.

When several coroutines call afterPrevious at the same time, they will queue up in the order that they call afterPrevious. Then, one coroutine will enter the block at a time.

In the following example, only one save operation (user or song) will be executing at a time.
```kotlin
val singleRunner = SingleRunner()

fun saveUser(user: User) {
    singleRunner.afterPrevious { api.post(user) }
}

fun saveSong(song: Song) {
    singleRunner.afterPrevious { api.post(song) }
}
```

#### ControlledRunner
A controlled runner decides what to do when new tasks are run.

By calling [joinPreviousOrRun], the new task will be discarded and the result of the previous task will be returned. This is useful when you want to ensure that a network request to the same resource does not flood.
```kotlin
val controlledRunner = ControlledRunner<Product>()

fun sortAscending(): List<Product> {
    return controlledRunner.cancelPreviousThenRun { dao.loadSortedAscending() }
}
```

By calling [cancelPreviousThenRun], the old task will *always* be cancelled and then the new task will be run. This is useful in situations where a new event implies that the previous work is no longer relevant such as sorting or filtering a list.
```kotlin
val controlledRunner = ControlledRunner<Product>()

fun sortDescending(): List<Product> {
    return controlledRunner.cancelPreviousThenRun { dao.loadSortedDescending() }
}
```
