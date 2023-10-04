# Project diary

Development machine:
- Apple M1 Max MacBook Pro with 64 GB of memory
- MacOS Sonoma 14.0
- XCode 15.0
- Android Studio Giraffe 2022.3.1 Patch 2
- OpenJDK 16 (from Homebrew, see diary below)

## Wednesday, Oct 4th 2023

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
