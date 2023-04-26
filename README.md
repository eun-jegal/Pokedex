<h1>Pokedex</h1>
<p>📱 </p>
<br>

<!-- Screenshots -->
<p align="center">
  <img src="https://user-images.githubusercontent.com/57670625/234713533-c64cfbcf-e796-455a-be22-dc82450b8ed1.png" width="35%"/>
  &nbsp;                                                                                                                
  <img src="" width="35%"/>
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
<li>Jetpack Compose: r</li>
<li>ViewModel: Exposes data streams as a state holder</li>
<li><a href="https://developer.android.com/training/dependency-injection/hilt-android">Hilt</a>: Dependency injection library built on top of Dagger benefit from the compile-time correctness, runtime performance, scalability, and Android Studio support </li>
<li><a href="https://developer.android.com/kotlin/coroutines">Coroutines</a>: Concurrency design pattern provided by Kotlin</li>
<li><a href="https://square.github.io/retrofit/">Retrofit</a>: Type-safe REST client for Android, Java and Kotlin developed by Square.  </li>
<li><a href="https://square.github.io/okhttp/">OkHttp</a> : 3rd party library sending and receive HTTP-based network requests built on top of the Okio library</li>
<li><a href="https://github.com/google/gson">GSON</a>: Java library that can be used to convert Java Objects into their JSON representation</li>
</ul>
<br>

<!-- Architecture -->
<h2>Architecture</h2>
<p>Top News app was built with Google's recommended modern app architecture - MVVM pattern. By separating multiple app components into two main layers
- UI and Data, the app is scalable, maintainable and testable.</p>
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
   <img src="https://user-images.githubusercontent.com/57670625/233807375-753576de-7e67-4c87-87d3-680a5fb9d821.jpg"/>
</p>

<h3>Architecture Overview</h3>
<p>Top News is composed with three different layers - UI layer, domain layer and data layer. Each layer has app components which have different responsibilities.</p>

<h3>UI Layer</h3>
<p align="center">
   <img src="https://user-images.githubusercontent.com/57670625/233807373-7456312c-af7c-424c-a361-325c3e750919.jpg" width="85%"/>
</p>
<p>UI layer displays the application data and serves as the primary point for user interactions. Whenever the app data changes, the UI should update to reflect changes made by either user interaction or external input.</p>
<ul>
  <li>The main activity and all the fragments - Feed, Browse, Saved, etc are UI elements and they display articles received from network requests and the database</li>
  <li>NewsViewModel holds state and plays as a bridge between UI elements and the data layer</li>
  <li>UI elements request actions to ViewModel and observer ViewModel's livedatas to automatically update screens</li>
</ul>
<br>

<h3>Data Layer</h3>
<p align="center">
   <img src="https://user-images.githubusercontent.com/57670625/233807461-88d0510e-3da2-4dfc-b763-bc23b8d11fa2.jpg" width="85%"/>
</p>
<p>Data layer is reponsible for containing application data and business logics. The data layer is consisted of repositories and data sources. It is important to keep each repository as a single source of truth.</p>
<ul>
  <li>NewsRepository is a single source of truth and requests data from NewsLocalDataSource and NewsRemoteDataSource.</li>
  <li>NewsLocalDataSource is a class managing the database built with Room library and NewsRemoteDataSource is a class requesting network response to NewsAPI server.</li>
</ul>
<br>

<!-- Open APIs -->
<h2>Open APIs</h2>
<p>Top News using the <a href="https://newsapi.org//">NewsAPI</a> for fetching JSON object from the server. News API provides articles and breaking news headlines from news sources and blogs across the web with JSON API.</p>
 
