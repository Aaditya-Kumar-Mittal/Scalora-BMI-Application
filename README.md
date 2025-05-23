# ⚖️ Scalora BMI Application

A modern Android app built with **Kotlin** for calculating **Body Mass Index (BMI)**, with a full authentication flow and persistent data storage. It combines elegant **UI components** with robust **SharedPreferences**-based state management.

![Platform](https://img.shields.io/badge/platform-Android-green) ![Language](https://img.shields.io/badge/language-Kotlin-purple)

---

## Screenshots

### ![Image - 1](./screenshots/1.png)

### ![Image - 2](./screenshots/2.png)

### ![Image - 3](./screenshots/3.png)

---

## 📱 Features

- 🔥 **Splash Screen** with smooth transition
- 👤 **Sign Up / Login** screens using `SharedPreferences`
- 📋 **Information Screen** for user context
- ⚖️ **BMI Calculator** with:

  - Gender selection
  - Height slider
  - Editable weight and age fields
  - Progress bar for feedback

- 🧠 **Smart Persistence**: User inputs and calculated BMI are retained
- 🧩 **Modular UI** using Fragments, `ViewBinding`, and `CardViews`

---

## ⚙️ Tech Stack

- **Language**: Kotlin
- **Architecture**: Activity + Fragment-based
- **UI**: ConstraintLayout, CardView, TableLayout, ProgressBar
- **State Persistence**: SharedPreferences
- **Navigation**: BottomNavigationView + FragmentManager
- **UX**: Splash Screen, Loading Bar, ViewBinding

---

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog or later
- Android SDK 33+
- Gradle 8+

### How to Run

1. Clone the repo:

   ```bash
   git clone https://github.com/Aaditya-Kumar-Mittal/Scalora-BMI-Application.git
   ```

2. Open the project in Android Studio.
3. Sync Gradle and build the project.
4. Run the app on an emulator or physical device.

---

## 🧠 Logic Highlights

- **BMI Calculation**:

  ```kotlin
  val bmi = weight / (height * height)
  ```

- **SharedPreferences** used for storing:

  - User login state
  - Profile data (name, email, password)
  - BMI input values and result

---

## 🧑‍💻 Author

👨‍💻 [Aaditya Kumar Mittal](https://github.com/Aaditya-Kumar-Mittal)

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
