<h1>Pokedex</h1>
<p>📱 Pokedex App built with MVVM pattern using Jetpack Compose, Coroutines, Retrofit and Retrofit</p>
<br>

<!-- Screenshots -->
<p align="center">
  <img src="https://user-images.githubusercontent.com/57670625/234713533-c64cfbcf-e796-455a-be22-dc82450b8ed1.png" width="30%"/>
  &nbsp;                                                                                                                
  <img src="https://user-images.githubusercontent.com/57670625/235319759-4c7bb1a8-5180-49b7-b31f-4f15e81e29c0.png" width="30%"/>
  </p>
<br>

<!-- Tech Stack -->
<h2>Tech Stack</h2>
<ul>
<li>Minumum SDK Level: 21</li>
<li>100% Kotlin</li>
<li>Architecture
    <ul>
      <li><a href="https://developer.android.com/topic/architecture">MVVM Pattern</a>: Industry-recognized software architecure pattern supported by Google</li>
    </ul>
 </li>
<li><a href="https://developer.android.com/jetpack/compose/documentation">Jetpack Compose</a>: Modern toolkit for building Android user interfaces using a declarative programming model</li>
<li><a href="https://developer.android.com/jetpack/compose/state">ViewModel</a>: Exposes data streams as a state holder</li>
<li><a href="https://developer.android.com/training/dependency-injection/hilt-android">Hilt</a>: Dependency injection library built on top of Dagger benefit from the compile-time correctness, runtime performance, scalability, and Android Studio support </li>
<li><a href="https://developer.android.com/kotlin/coroutines">Coroutines</a>: Concurrency design pattern provided by Kotlin</li>
<li><a href="https://square.github.io/retrofit/">Retrofit</a>: Type-safe REST client for Android, Java and Kotlin developed by Square.  </li>
<li><a href="https://square.github.io/okhttp/">OkHttp</a> : 3rd party library sending and receive HTTP-based network requests built on top of the Okio library</li>
<li><a href="https://github.com/google/gson">GSON</a>: Java library that can be used to convert Java Objects into their JSON representation</li>
</ul>
<br>

<!-- Architecture -->
<h2>Architecture</h2>
<p>Pokedex demonstrates MVVM architecture by separating multiple app components into two main layers - UI and Data. Following the core Android design principles, the app is scalable, maintainable and testable.</p>
<ul>
  <li>Architectural Principles</li>
    <ul>
      <li>Separations of concerns</li>
      <li>Drive UI from data models</li>
      <li>Single source of truth</li>
      <li>Unidirectional Data Flow</li>
   </ul>
</ul>
<p align="center">
   <img src="https://user-images.githubusercontent.com/57670625/235320692-23885120-7757-4ab9-868f-4bc42eec135d.jpg" width="85%"/>
</p>

<h3>Architecture Overview</h3>
<p>Pokedex App follows the unidirectional data flow by adapting architectural layering. The app responds accordingly to user events and update ui states.</p>

<h3>UI Layer</h3>
<p align="center">
   <img src="https://user-images.githubusercontent.com/57670625/235320690-5a3cb2a2-1473-432c-bff5-8379241bcc22.jpg" width="85%"//>
</p>
<p>UI layer displays the application data and serves as the primary point for user interactions. Whenever the app data changes, the UI should update to reflect changes made by either user interaction or external input.</p>
<ul>
  <li>The main activity hosts the navigation controller and navigates through two screens - PokemonListScreen and PokemonDetailScreen depending on user interactions. </li>
  <li>PokemonListViewModel requests data to the data layer and PokemonListScreen updates UI observing states held by ViewModel.</li>
  <li>PokemonDetailViewModel is responsible for fetching pokemon details and updates states for PokemonDetailScreen.</li>
</ul>
<br>

<h3>Data Layer</h3>
<p align="center">
   <img src="https://user-images.githubusercontent.com/57670625/235320689-16de6724-0570-43ac-aa4d-786f879ffeb0.jpg" width="65%"/>
</p>
<p>Data layer is reponsible for containing application data and business logics. The data layer is consisted of repositories and data sources. It is important to keep each repository as a single source of truth.</p>
<ul>
  <li>PokemonRepository is a single source of truth and requests data from the remote data source.</li>
  <li>PokemonRepository requests network responses to PokeAPI server using Retrofit library.</li>
</ul>
<br>

<!-- Open APIs -->
<h2>Open APIs</h2>
<img src="https://user-images.githubusercontent.com/57670625/235320883-b4eae21e-93c0-4229-abaf-642d1cb4bc34.png" align="right" width="21%"/>
<br>
<p>Pokedex uses <a href="https://pokeapi.co/">PokeApi</a> for fetching JSON object from the server. PokeApi provides a full RESTful API linked to an extensive database detailing everything about the Pokémon main game series.</p>
<br>

<!-- References -->
<h2>References</h2>
<p>This project was built to understand how to build a clean architecture Jetpack Compose app using differnt types of Android libraries.</p>
<li><a href="https://www.youtube.com/watch?v=v0of23TxIKc&list=PLQkwcJG4YTCTimTCpEL5FZgaWdIZQuB7m">Youtube Tutorial</a>: Step-by-step tutorial to build Android Pokedex app using PokeApi</li>
