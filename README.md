# Tic Tac Toe Android Game

A two-player Tic Tac Toe game for Android devices.

## Features

- Two players play on one Android device
- Player X goes first, followed by Player O
- Win detection (rows, columns, diagonals)
- Draw detection
- Game end dialog with winner message
- Play Again button to restart the game
- Clean and modern UI

## Requirements

- Android Studio Hedgehog or later
- Minimum SDK: 24 (Android 7.0)
- Target SDK: 34 (Android 14)
- Kotlin support
- Gradle 8.2+

## Building and Running

**📖 For detailed step-by-step instructions, see [RUNNING.md](RUNNING.md)**

### Quick Start (Android Studio):

1. Open the project in Android Studio
2. Sync Gradle files (the project uses Kotlin DSL)
3. Create/start an Android emulator (Tools → Device Manager → Create Device)
4. Click the green "Run" button (▶) or press `Ctrl+R` (Mac) / `Shift+F10` (Windows/Linux)
5. Select your device/emulator and click OK
6. The app will build, install, and launch automatically!

### Command Line:

```bash
# Build the app
./gradlew build

# Install to connected device/emulator
./gradlew installDebug

# Launch the app
adb shell am start -n com.example.tictactoe/.MainActivity
```

## How to Play

1. Player X starts the game
2. Tap on any empty cell to place your mark
3. Players alternate turns
4. The first player to get three marks in a row (horizontally, vertically, or diagonally) wins
5. If all cells are filled without a winner, it's a draw
6. When the game ends, a dialog will show the result
7. Click "Play Again" to start a new game

## Project Structure

```
.
├── app/
│   ├── build.gradle.kts              # App module build configuration (Kotlin DSL)
│   ├── proguard-rules.pro
│   └── src/main/
│       ├── java/com/example/tictactoe/
│       │   └── MainActivity.kt      # Main game logic
│       ├── res/
│       │   ├── layout/
│       │   │   └── activity_main.xml # Game UI layout
│       │   └── values/
│       │       ├── strings.xml      # String resources
│       │       ├── colors.xml       # Color definitions
│       │       └── themes.xml       # App theme
│       └── AndroidManifest.xml
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── build.gradle.kts                  # Root build configuration (Kotlin DSL)
├── settings.gradle.kts               # Project settings (Kotlin DSL)
├── gradle.properties
├── gradlew                           # Gradle wrapper script (Unix)
├── gradlew.bat                       # Gradle wrapper script (Windows)
└── README.md
```

## Game Logic

The game uses a 3x3 array to track the game state. Win conditions are checked after each move:
- Three in a row (horizontal)
- Three in a column (vertical)
- Three diagonally (both directions)

If no winner is found and the board is full, the game ends in a draw.

## Technology Stack

- **Language**: Kotlin
- **Build System**: Gradle with Kotlin DSL
- **UI**: XML Layouts with ViewBinding
- **Minimum Android Version**: API 24 (Android 7.0)
