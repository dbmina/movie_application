# movie_application
This is a simulator of movie application using JAVA


Objectives : Implement a movie application to search, rate, and recommend movies.     
Descriptions: You are a senior developer in an entertainment content provider, Netcha. You are asked to implement a system for users to search for movies, rate the movies, and get
recommendations. In particular, you will need to implement various methods in the MovieApp class in MovieApp.java         
● User and Movie classes are provided in User.java and Movie.java, respectively      

Function 1: Basic Methods for MovieApp Class    

Objectives: Implement the addMovie , addUser , findMovie , and findUser methods of the MovieApp class.          
Descriptions: The MovieApp class manages the information of all movies and users. As a start, I implemented the following four basic methods to add/find movies/users inside the
MovieApp class.     
To implement these methods, you will need to decide on the appropriate collections (e.g., List , Set , Map , etc.) to store Movie objects and User objects, and add a few lines of code to declare and initialize the collections.     
● public boolean addMovie(String title, String[] tags) creates a Movie object with the title and tags and saves it in the collection.     
○ If the Movie object with the same title was already registered, do not create a Movie object and return false.      
○ Return true if a new Movie object is created and stored successfully.      
● public boolean addUser(String name) creates a User object with the username and saves it in the collection.    
○ If there already exists a user with the given username , do not create a User object, and return false.     
○ Return true if a new User object is created and stored successfully.       
● public Movie findMovie(String title) returns a Movie object with the given title .       
○ Return null if there is no movie with the given title .         
● public User findUser(String username) returns a User object with the given username .          
○ Return null if there is no user with the given username .      

Function 2: Movie Searching with Tags     
Objectives: Implement the findMoviesWithTags method of the MovieApp class.    
Descriptions: Implement the method to search for the movies matching the given tags.       
● Implement the public List<Movie> findMoviesWithTags(String[] tags) method.      
● The method returns a List of Movie objects matching all given tags .          
○ If the given tags are a subset of the tags of a movie, the movie should be included in the output list. Otherwise, the move should not be included.     
○ There should be no duplicated movies in the output list.      
○ The output list should be in the lexicographical order of the title of a movie.      
■ e.g. [“Abatar”, “Heart Locker”, “Jocker”, “Ra Ra Land”]       
○ If the given tags match none of the movies, the empty list should be returned.          
○ If the empty String array is given as a tags argument, the empty list should be returned.     
  
  
Function 3: Movie Rating         

Objectives: Implement the rateMovie , getUserRating methods of the MovieApp class.       
Descriptions: I implemented two methods: (1) to rate a movie and (2) to get a rating.         

● public boolean rateMovie(User user, String title, int rating)      
○ This method adds a rating of a movie from a user. Note that it is necessary to store which movie was rated, who rated the movie, along with the rating score.      
Decide on an appropriate collection to store the rating information.          
○ Do nothing and return false in one of the following cases.         
■ The title is null, or there is no movie with the given title.        
■ The user is null or not registered.          
■ The given rating is out of range. The valid rating should be between 1 <=(rating) <= 10.       
○ Otherwise, store the rating information and return true.      
○ If a user rates the same movie multiple times, only the last rating is stored.         
● public int getUserRating(User user, String title)         
○ This method returns the rating of the user for the movie with the given title.          
○ If the user or the movie with the title is null or not registered, return -1,          
○ If the user has not rated the movie, return 0.        

Function 4: Movie Recommendation       
Objectives: Implementing the recommend and findUserMoviesWithTags methods of the MovieApp class.     
Descriptions: I implemented a method to recommend movies using a user's search history.         
● As the first step, it is needed to implement the public List<Movie> findUserMoviesWithTags(User user, String[] tags) method to store the search history of a user.     
  The search history is used for the recommendation.       
○ This method does two tasks: i) stores the search history (which will be used by the recommend method described below), and ii) returns the list of movies matching the given tags.      
○ If the user is null or not registered, do nothing and return the empty list.          
○ Otherwise, i) use the findMoviesWithTags method (implemented in Function 2) to find matching movies, ii) store the list of searched movies along with the user information, and ii) return the matching movies.      
● Then, you need to Implement the public List<Movie> recommend(User user) method that returns 3 movies to recommend to the given user.      
○ How do we decide those 3 movies to recommend?     
■ First, the candidates are the outputs of the findUserMoviesWithTags the user has called.      
■ Sort the candidate movies based on two criteria. First, sort by the average ratings (descending order).         
  When multiple movies have the same average ratings, sort them by lexicographical order of the titles. Recommends the first three movies and returns them as a list of the Movies objects.       
○ If the user is null or not registered, return the empty list.       
○ If the movie is not rated before, its average rating is 0.       
○ If there are less than 3 movies to recommend, then the returned list may contain less than 3 movies. If there is no movie to recommend, return the empty list.     
