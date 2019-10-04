# qcs_test

## Libraries used
* *Koin* : Dependency injection - provides a simple dependency injection solution.
* *Picasso* : Image loading.
* *Retrofit* : Typesafe Http client

## TODO
* finish ViewModel tests : Approach is to set a single 'thread' mode for coroutines then suspend function execution can be tested inline. 

# Improvements
* Use the jetpack paging library to page the data from the api and support list scrolling
* Add a loading state enum to correctly update the load state in the UI
