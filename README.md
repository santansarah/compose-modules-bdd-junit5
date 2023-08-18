# Jetpack Compose Multi-Module Clean Architecture

This is a port of my [City Api Client](https://github.com/santansarah/city-api-client) to model the
Perry Street Software pattern: https://github.com/perrystreetsoftware/DemoAppAndroid

## Understanding the Data Flow (Network -> Repository) the RxJava Way

1. API calls return:
    * Observable - Very similar to Kotlin cold flow. Used for emitting streams of data over
      time whose state you want to track. Good for collections and lists. Will only emit when a
      observer is subscribed.
    * Completeable - This has no value; it only emits Completed or Error. Good for things that
      return void - like setting a favorite, liking something, etc.
    * Single - Emits a single successful value or an error. This is good for pulling up detail
      items, like a single ToDo item, or a City Result, etc. It is comparable to `mutableStateOf`.

   Each API call is set to start in the IO thread: `Schedulers.io()`. Calls are also deferred, which
   means that values aren't emitted until someone subscribes. This gives each subscriber their own
   flow of data, independent of other subscribers. Flows will be repeated - NOT hot, for new
   subscribers.

2. Now, we want to call this API from our repositories. Repositories can be:
    * Pull - synchronous-like iterator pattern, where the data source would need to be polled to
      get any changes. So if we added or changed data, we need code to manually check for this. Good
      for getting some type of detail that's not going to actively change.
    * Push - This is an active, steady stream of data that's sent off to the subscriber and triggers 
      new state changes. To do this, the repo uses a BehaviorSubject that requires an initial 
      value and emits the current value to new subscribers. When an observer subscribes to a 
      BehaviorSubject, it gets the default value if nothing is emitted, the most recent value if all
      is good, or an error if something goes wrong.

## First Passing Test at DTO layer:

![dto](dto-test-pass.png "DTO")

## Second Passing Test at Repository Layer:

![repo](repository-test-pass.png "Repository")