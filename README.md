# WorkoutApp

WorkoutApp is a comprehensive fitness tracking app that combines the functionalities of a running tracker and a 7-minute workout guide. The app allows users to track and monitor their running activities, view their running history and statistics, and perform high-intensity, full-body exercises designed to be completed in just 7 minutes.

## Usage

To use WorkoutApp, follow these steps:

### Setup
1. Open the app on your device.
2. Enter your name and weight in the setup screen and click on the "Continue" button.

### Running Tracker
1. Click on the "Floating Action Button" then "Start" button in the tracking screen to start tracking your running activity.
2. During the run, you can see your current location on the map view and the timer will keep track of your running time.
3. Once you are done with the run, click on the "Stop" button to end the tracking.
4. View your running history in the "Your Runs" screen and sort them using various filters.
5. View your overall statistics in the "Statistics" screen, including total distance ran, total time, total calories burned, and average speed.

### 7-Minute Workout
1. On the home screen, tap the "Start Workout" button to begin your 7-minute workout.
2. Follow the on-screen instructions to complete each exercise.
3. View your workout history and BMI calculation by tapping on the corresponding buttons on the home screen.

### Settings
- Update your name and weight in the "Settings" screen.

## Technologies Used

WorkoutApp was built using the following technologies:
- Android Jetpack Components
- MVVM architecture
- Navigation Components
- Retrofit
- Google Maps API
- Kotlin
- RecyclerView
- Dagger-Hilt for dependency injection
- Kotlin Coroutines

## Screenshots

### Run Fragment
This fragment shows a list of saved runs, which can be sorted using various filters such as date, running time, distance, average speed, and calories burned.

<img src="https://res.cloudinary.com/dixttklud/image/upload/v1674788355/RunningAppSS/Screenshot_20230126-110023_RunningApp_nlwwm1.jpg" width="20%" height="20%">

### Tracking Fragment
In this fragment, users can see their location on a map and stop or pause the run. A stopwatch is also available for reference, and a foreground service runs in the background to track the user's location.

<img src="https://res.cloudinary.com/dixttklud/image/upload/v1674788372/RunningAppSS/Screenshot_20230126-110101_RunningApp_mkrosf.jpg" width="20%" height="20%">

### Statistics Fragment
This fragment displays statistics of the user's total runs, such as total average speed, total time ran, total calories burned, and total distance ran.

<img src="https://res.cloudinary.com/dixttklud/image/upload/v1674788390/RunningAppSS/Screenshot_20230126-110122_RunningApp_hl9122.jpg" width="20%" height="20%">

### Setup Fragment
In this fragment, users can store their data (name and weight). Shared Preferences are used to store and update the data.

<img src="https://res.cloudinary.com/dixttklud/image/upload/v1675011114/RunningAppSS/Screenshot_20230129_221931_zl0frd.png" width="20%" height="20%">

### Settings Fragment
In this fragment, users can update their data (name and weight).

<img src="https://res.cloudinary.com/dixttklud/image/upload/v1720790179/Settings_p0tbhp.png" width="20%" height="20%">

### Home Screen

<img src="https://res.cloudinary.com/dixttklud/image/upload/v1720790007/7MinHomeScreen_nbrlnq.png" width="20%" height="20%">


### Exercise Activity

<p align="center">
  <img src="https://res.cloudinary.com/dixttklud/image/upload/v1720790459/Screenshot_20240712_184906_osgogo.png" width="20%" height="20%">
  <img src="https://res.cloudinary.com/dixttklud/image/upload/v1720790459/Screenshot_20240712_184917_nhfji6.png" width="20%" height="20%">
</p>

## Installation

To install WorkoutApp, follow these steps:
1. Clone the repository to your local machine using `git clone https://github.com/tanmaySinha16/WorkoutApp.git`.
2. Open the project in Android Studio.
3. Build and run the app on an emulator or physical device.

Note: Make sure to allow location permissions for the app to track your running activity.

## Conclusion

WorkoutApp is a great tool for fitness enthusiasts who want to keep track of their running activity and improve their performance while also benefiting from high-intensity, full-body workouts. It uses modern Android development practices and libraries to provide a seamless and user-friendly experience.
