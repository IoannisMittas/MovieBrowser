# MovieBrowser
Android app in Kotlin that fetches and displays movie data in a list.  

The app architecture is base on Clean Architecture. (https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).
It is separated on three main modules, in order to separate concerns, minimize dependencies and provide a clean architecture for the app to be scalable, testable and clean. 
The modules are: 
### Domain
This is the module in the core of the app, containing the business logic. It has no dependencies on other libraries, only in RxJava because it's very common (no RxAndroid!).

### Data 
The data module is responsible for obtaining and storing data. For example, Retrofit calls are implemented here. If we would add persistance to the app (eg Room), it would
go here as well. 

### Presentation
The presentation (app) module is responsible for UI interaction and presenting the data. This is where Activities, Fragments, ViewModels etc are existing.
Also, this is the module where dependency injection is taking place, as it is the main module of the app. 

In order for all the modules to communicate with each other using minimal dependencies and adhering to Dependency Inversion principle, dependency injection is taking place, using Dagger2 as a tool. 

Here is a helpful diagram of the architecture, which we could say it's the "Android" take on the classic concentric circles of Clean Architecture.

![Clean Android](https://miro.medium.com/max/1888/1*vcnYWWn_zhNk6I30meBaPg.png)

In addition, MVVM design pattern is used, with the help of LiveData and ViewModel. A Resource class is used in the presentation module, in order to encapsulate the data state
(LOADING, SUCCESS, ERROR). This is used, for example, in order to begin and stop the loading animation. 

### Testing
Tests were written for testing the viewmodel, the usecase, and the repository. The tests exist in test directory, in each respected module (app, data, domain).

## Tools
RxJava3\
RxAndroid\
Dagger2
Retrofit\
OkHttp\
JUnit\
Mockito\
GSON\
Timber\
Android Architecture Components\
Glide

