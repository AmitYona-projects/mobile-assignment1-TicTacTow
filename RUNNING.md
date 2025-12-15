# How to Run the Tic Tac Toe App

## Option 1: Using Android Studio (Recommended)

### Prerequisites
1. **Install Android Studio** (if not already installed)
   - Download from: https://developer.android.com/studio
   - Make sure to install Android SDK and emulator during setup

### Steps to Run:

1. **Open the Project**
   - Launch Android Studio
   - Click "Open" or "File → Open"
   - Navigate to: `/Users/amitetrogy/Desktop/mobile_assignments/mobile-assignment1-TicTacTow`
   - Select the folder and click "Open"

2. **Wait for Gradle Sync**
   - Android Studio will automatically detect the project
   - It will sync Gradle files (this may take a few minutes the first time)
   - Wait for "Gradle sync finished" message at the bottom

3. **Set Up an Emulator (if you don't have a physical device)**
   - Click "Device Manager" icon in the toolbar (or Tools → Device Manager)
   - Click "Create Device"
   - Select a device (e.g., "Pixel 5" or "Pixel 6")
   - Click "Next"
   - Download a system image (e.g., "Tiramisu" API 33 or "UpsideDownCake" API 34)
   - Click "Next" → "Finish"
   - Click the "Play" button next to your emulator to start it

4. **Run the App**
   - Make sure an emulator is running OR a physical device is connected via USB
   - Click the green "Run" button (▶) in the toolbar
   - Or press `Shift + F10` (Windows/Linux) or `Ctrl + R` (Mac)
   - Select your device/emulator from the list
   - Click "OK"

5. **View the UI**
   - The app will build and install on your device/emulator
   - The Tic Tac Toe game will launch automatically
   - You should see a 3x3 grid with buttons and a status text at the top

## Option 2: Using Command Line

### Prerequisites
1. **Install Android SDK** (comes with Android Studio)
   - Set `ANDROID_HOME` environment variable
   - Add `$ANDROID_HOME/platform-tools` to your PATH for `adb`

2. **Have a device/emulator running**
   - Start an emulator from Android Studio, OR
   - Connect a physical device via USB with USB debugging enabled

### Steps to Run:

1. **Navigate to project directory**
   ```bash
   cd /Users/amitetrogy/Desktop/mobile_assignments/mobile-assignment1-TicTacTow
   ```

2. **Check if device is connected**
   ```bash
   adb devices
   ```
   You should see your device/emulator listed

3. **Build and install the app**
   ```bash
   ./gradlew installDebug
   ```

4. **Launch the app**
   ```bash
   adb shell am start -n com.example.tictactoe/.MainActivity
   ```

   Or simply open it manually from the app drawer on your device.

## Troubleshooting

### "Gradle sync failed"
- Make sure you have internet connection
- Try: `File → Invalidate Caches → Invalidate and Restart`
- Check that you have Java JDK installed (Android Studio usually handles this)

### "No devices found"
- Make sure emulator is running (check Device Manager)
- For physical device: Enable USB debugging in Developer Options
- Try: `adb kill-server && adb start-server`

### "Build failed"
- Make sure you're using Android Studio Hedgehog or later
- Check that Kotlin plugin is installed
- Try: `./gradlew clean` then rebuild

### App crashes on launch
- Check Logcat in Android Studio for error messages
- Make sure minimum SDK (24) is supported by your device/emulator

## Quick Test

Once the app is running, you should see:
- ✅ Status text showing "Player X's Turn"
- ✅ A 3x3 grid of empty buttons
- ✅ "Play Again" button (hidden until game ends)

Try clicking a button - it should show "X" and switch to "Player O's Turn"!

