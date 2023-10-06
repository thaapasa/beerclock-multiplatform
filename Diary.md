# Project diary

Development machine:

- Apple M1 Max MacBook Pro with 64 GB of memory
- MacOS Sonoma 14.0
- XCode 15.0
- Android Studio Giraffe 2022.3.1 Patch 2
- OpenJDK 17 (from Homebrew, see diary below)

## Links to resources

Compose Multiplatform

- [Project page](https://www.jetbrains.com/lp/compose-multiplatform/)
- [Getting started -guide](https://github.com/JetBrains/compose-multiplatform/#readme)
- [Project template](https://github.com/JetBrains/compose-multiplatform-ios-android-template#readme)
- [Example projects](https://github.com/JetBrains/compose-multiplatform/tree/master/examples)

Jetpack Compose
- [Documentation](https://developer.android.com/jetpack/compose)
- [Material UI components](https://developer.android.com/jetpack/compose/components)

Other resources
- [Awesome Kotlin Multiplatform](https://github.com/terrakok/kmp-awesome#-compose-ui)

## Creating the project template

_Wednesday, Oct 4th 2023_

Getting project template running on all platforms:

- Located Jetpack [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/).
- Generated project from
  [template](https://github.com/JetBrains/compose-multiplatform-template).
- Installed OpenJDK 21 (and pointed `JAVA_HOME` to it):

  ```sh
  brew install openjdk
  ````

  Although it seems that Android Studio will use the bundled Java 17
  instead. Oh well.
- Installed and run KDoctor:

  ```sh
  brew install kdoctor
  kdoctor
  # and for more details
  kdoctor -v
  ````

- Updated [Android Studio](https://developer.android.com/studio)
  to latest version (Giraffe, 2022.3.1 Patch 2)
- Installed Kotlin Multiplatform Mobile
  [plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile).
- `./gradlew run` from command line threw error:

  ```
  Unknown Kotlin JVM target: 21
  ```

  Okay, so this Compose setup seems to require Java 17.
  Some googling reveals that it might be possible to use Java 21 but
  just target Java 17.
- Installed OpenJDK 17 (and pointer `JAVA_HOME` to it):

  ```sh
  brew install openjdk@17
  ```

- `./gradlew run` works! 🎉
- Installed Android emulator targeting API 34 (Android 14).
- `./gradlew installDebug` works and installs app on emulator. Similarly
  running from Android Studio works.
- Using instructions from
  [template readme](https://github.com/JetBrains/compose-multiplatform-template#readme),
  selected iOS target for iOS app and was able to run app on iOS
  simulator as well!
- Tried changing the text shown on app main screen. There is no hot
  reload, but you have to rebuild and reinstall the apps. For iOS
  this is a bit slow. For Android it can rebuild and restart only
  the activity, so the update is quite fast. Android Studio reports
  that `Apply Changes successfully finished in 36ms` but the emulator
  updated after about a second.
- We have a working app!

## Learning Compose UI

_Wednesday, Oct 4th 2023_

- To get started with Compose, I copy-pasted the example application
  [main class](./shared/src/commonMain/kotlin/App.kt) code to ChatGPT+,
  with this leading text:

  > This is the sample code file from a Jetpack Compose Multiplatform
  template project. Can you go through it line by line and explain what
  the lines mean? I have basic knowledge of Kotlin but not of Compose.

  The results were quite informative, like:

  ```
  @Composable

  The @Composable annotation indicates that the function is a
  composable function. Composable functions can be called and will
  produce UI. This is a core concept in Jetpack Compose.
  ```

  and

  ```
  MaterialTheme {

  This applies the MaterialTheme, which is a part of Jetpack Compose's
  Material Design components. It provides consistent theming for your
  UI components.
  ```

  and

  ```
  var greetingText by remember { mutableStateOf("Hello, World!") }

  Here, a mutable state is being declared for greetingText. remember is
  a function that remembers a value across recompositions.
  mutableStateOf provides a way to hold state that can trigger
  recomposition when changed. The initial value of greetingText is
  "Hello, World!".
  ```
- Had more conversations with ChatGPT and also Compose's own
  documentation and added a standard Material UI menu bar to the app.
  Next step: learn about `suspend` functions and how to open a drawer
  from top menu buttons.
- Noticed that looking at Jetpack Compose documentation can be
  misleading since not everything seems to be available out-of-the-box.
  Using just Jetpack Compose might be simpler, but I'm not ditching
  iOS support just yet.
- Compose Multiplatform did not seem to have all the components readily
  available, so I could not create e.g. the navigation drawer menu
  exactly like the
  [Compose example](https://developer.android.com/jetpack/compose/components/drawer)
  shows. Luckily I found
  [this example](https://www.netguru.com/blog/multiplatform-adaptive-ui)
  for creating UIs for Compose Multiplatform.
- Created openable side menu. Yay! 🙌

## What about localization?

_Wednesday, Oct 4th 2023_

- It seems that localization may not be directly supported, at least in
  a similar way as it's supported for native Android apps.
  The ImageViewer
  [example project](https://github.com/JetBrains/compose-multiplatform/blob/master/examples/imageviewer),
  for example, uses custom
  [localization code](https://github.com/JetBrains/compose-multiplatform/blob/master/examples/imageviewer/shared/src/commonMain/kotlin/example/imageviewer/Localization.kt).
  Some [discussion](https://github.com/JetBrains/compose-multiplatform/issues/425)
  on this topic.
- Created custom
  [localization code](./shared/src/commonMain/kotlin/Localization.kt)
  to the project.
- Added localization customization to Android project
  [configuration](./androidApp/build.gradle.kts):

  ```groovy
  androidResources {
    generateLocaleConfig = true
  }
  ```

  Also added localizations for the project name under Android string
  resources. This was enough to allow changing the project language
  in Android's own app configuraten menu, and it works with the
  custom localization code.

## App state

_Thursday, Oct 5th 2023_

- Moving on to state handling. And example
  [todo app](https://github.com/JetBrains/compose-multiplatform/blob/master/examples/todoapp-lite)
  has some examples of
  [state handling](https://github.com/JetBrains/compose-multiplatform/blob/master/examples/todoapp-lite/shared/src/commonMain/kotlin/example/todoapp/lite/common/RootStore.kt).
  Started creating custom store for storing user settings so I can
  create a settings page for the app.
- ChatGPT was again helpful in reminding how enum classes work in
  Kotlin, what the `internal` visibility modifier means exactly, and
  how to type Composables that take other Composable as parameter
  (some lambda-wrapping was needed because `@Composable` references
  are not supported out-of-the-box).
- Noticed that Compose has a `@Preview` annotation for Composables
  that works with IDE to support showing a preview of the UI inside the
  IDE. But ... it's not supported for Multiplatform 🙄. There are
  [reports](https://slack-chats.kotlinlang.org/t/12111122/do-ide-previews-work-in-a-compose-multiplatform-setup-i-m-no)
  that suggest you can get it working for the Android-only code, but
  if I'm targeting multiplatform that's kinda missing the point.
- Text component is provided out-of-the-box by Compose (and works
  in Multiplatform as well), but couldn't find a standard dropdown
  selector. Googling found
  [this example](https://gist.github.com/snicmakino/297d34e429c078624fde6771064ed6d2?permalink_comment_id=4051239),
  so created a custom selector based on that.
- Storing user preferences is also something that needs to be manually
  handled for each platform. Google provided some
  [examples](https://medium.com/@shmehdi01/shared-preference-in-kmm-kotlin-multiplatform-2bca14214093),
  and ChatGPT also, with a similar approach. However, both of these
  failed to mention the Android-specific problem regarding `Context`s.
  More specifically, when working in the common, shared code, the
  context is not accessible at all, but it is required for some of
  the Android-specific functionality (such as storing preferences
  using `getSharedPreferences()`).
- Tried to work around the `Context` problem by using the
  `@Composable` annotation. This works, since the `Context` can be
  accessed from `@Composable` functions when in Android-specific
  code by using `LocalContext.current`. But then the
  `@Composable`-annotated functions cannot be called from any
  other scope (such as from inside a launched effect, or from a button
  `onClick` handler). And storing user preferences is anyway something
  that should not be a part of the `@Composable` flow.
- Finally gave up and used the late initialization techique described
  [here](https://proandroiddev.com/how-to-avoid-asking-for-android-context-in-kotlin-multiplatform-libraries-api-d280a4adebd2).
  Now the Android library implementation gets the Application context
  on startup, and just uses that directly.

## Navigation

_Thursday, Oct 5th 2023_

- Aha! Compose seems to be built on top of a "single-Activity" mindset.
  It seems that others have also noticed that state handling was a pain
  with the previous model of firing up new Activities and having to
  recreate state from scratch. This is promising!
- Unfortunately again it seems that for Compose
  [navigation](https://developer.android.com/jetpack/compose/navigation)
  is part of the standard library, but for Multiplatform the standard
  library is
  [not working](https://github.com/JetBrains/compose-multiplatform/tree/master/tutorials/Navigation)
  and you need to use
  [external libraries](https://github.com/terrakok/kmp-awesome#-compose-ui).
- Trying out [Appyx](https://bumble-tech.github.io/appyx/) next for
  navigation.


_Friday, Oct 6th 2023_

- Started integrating Appyx by adding the dependencies
  ```
  implementation("com.bumble.appyx:appyx-navigation:2.0.0-alpha08")
  api("com.bumble.appyx:backstack:2.0.0-alpha08")
  ```
  to the `commonMain` source set. It seems that Appyx is about to
  release the 2.x version of their library, but since their site
  already lists the 2.x version and doesn't state that it should not
  yet be used, I'm gonna try it out even though it's alpha.
- Started creating navigation based on the
  [Quick start guide](https://bumble-tech.github.io/appyx/navigation/quick-start/).
- Noticed that the code samples for the Desktop version seem to be
  out-of-date. No sense in banging my head against the wall here, since
  Desktop is not an interesting target for me.
- Dropped Desktop from the project.
- Noticed that running a full rebuild of the project (Rebuild Project
  from Android Studio) takes a really long time, like 5-10 minutes.
  Gradle also seems to get the caches broken if the Gradle build files
  are changed too much, especially when removing stuff or making
  larger changes to the project setup.
- To fix Gradle build, you may need to do any or all of the following:
  - run cleanup script `./cleanup.sh`
  - clean project with `./gradlew clean`
  - clear user Gradle caches from e.g. `~/.gradle/caches`
  - invalidate IDE caches (Android Studio: File → Invalidate Caches)

## TODO:

- Charts: [AAY-chart](https://github.com/TheChance101/AAY-chart)
