# Dicoding Story App

This is the final submission to graduate from the Android Application Intermediate Learning class organized by IDCampXdicoding.

![page-one](https://user-images.githubusercontent.com/108212568/221072944-c3ec792f-0c66-4f98-8db3-488c69f8933d.jpg)
![page-two](https://user-images.githubusercontent.com/108212568/221072959-b9308b69-b0f4-4dec-9bf6-f85ba430c5a1.jpg)

## Getting Started
Instructions on how to get a copy of the project up and running on your local machine.

### Prerequisites
- Android Studio
- JDK 19

### Instaling
Instructions on how to install and run the project on a local machine.

1. Clone the repository. 
    ```bash
    git clone https://github.com/tiochoirul/dicpdingstory-app.git
    ```
2. Open the project in Android Studio.
3. Build and run the project on an emulator or physical device.

## Features
- Login & Signup
- Unlimited Story List
- Story Detail
- Add New Story
- Location Story with Maps
- Localization (EN & ID)

## Libraries
- Android X
- Coroutine
- Retrofit
- Paging
- Maps
- Glide
- Room
- DataStore

## Architecture
Dicoding Story App is built using the MVVM (Model-View-ViewModel) architecture. This architecture separates the UI (View) from the data (Model) and the logic that connects them (ViewModel). Here's a brief overview of the different components in this architecture:

- Model: Contains the data and business logic of the app. In this app, the model is responsible for fetching movie data from a remote server and storing it in a local database.
- View: Displays the UI of the app to the user. In this app, the view is responsible for displaying a list of movies and their details.
- ViewModel: Connects the view with the model by providing data to the view and handling user interactions. In this app, the viewmodel is responsible for fetching data from the model and formatting it for display in the view.

## Contributing
If you would like to contribute to the project, please follow these steps:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature/new-feature`).
3. Make your changes.
4. Commit your changes (`git commit -am 'Add some feature`).
5. Push to the branch (`git push origin feature/new-feature`).
6. Create a new pull request.

## License
This project is licensed under the <a href="https://opensource.org/licenses/MIT">MIT License</a>.

## Contact Information
If you have any questions or feedback about Dicoding Story App, please contact us at mtio871@gmail.com.
