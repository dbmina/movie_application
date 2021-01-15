
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class MovieApp {

ArrayList<Movie> movielist=new ArrayList<Movie>();
   ArrayList<User> userlist=new ArrayList<User>();
    public boolean addMovie(String title, String[] tags) {
        // TODO sub-problem 1
    	
    	for(Movie movie: movielist) {
    		if (movie.getTitle().equals(title)) {
    			return false;
    		}
    	}
    	Movie new_movie=new Movie(title, tags);
    	movielist.add(new_movie);
        return true;
    }

    public boolean addUser(String name) {
        // TODO sub-problem 1
    	for(User user: userlist) {
    		if (user.getUsername().equals(name)) {
    			return false;
    		}
    	}
    	User new_user=new User(name);
    	userlist.add(new_user);
        return true;
    }

    public Movie findMovie(String title) {
        // TODO sub-problem 1
    for (Movie movie: movielist) {
    	if (movie.getTitle().equals(title)) {
    		return movie;
    	}
    }
        return null;
    }

    public User findUser(String username) {
        // TODO sub-problem 1
       for (User user:userlist) {
    	   if (user.getUsername().equals(username)) {
    		   return user;
    	   }
       }
       return null;
    }

    public List<Movie> findMoviesWithTags(String[] tags) {
        // TODO sub-problem 2
    	ArrayList<Movie> movie_list=new ArrayList<Movie>();
    	if (tags.length==0) {
    		return movie_list;
    	}
    
    	for (Movie movie:movielist) {
    		boolean containcheck=true;
    		ArrayList<String> mv_tag=new ArrayList<String>();
    		for (String tag: movie.getTags()) {
    			mv_tag.add(tag);
    		}
    		for (String tag: tags) {
    			if (!mv_tag.contains(tag)) {
    				containcheck=false;
    				break;
    			}
    		}
    		if (containcheck==true) {
    			movie_list.add(movie);
    		}
    	}
    	Collections.sort(movie_list);
    	
        return movie_list;
    }
    HashSet<Rate> hashset=new HashSet<Rate>();
    public boolean rateMovie(User user, String title, int rating) {
        // TODO sub-problem 3
    	
    	if (title==null || findMovie(title)==null) {
    		return false;
    	}
    	if (user==null|| findUser(user.getUsername())==null) {
    		return false;
    	}
    	if (!(rating>=1 &&rating<=10)) {
    		return false;
    	}
    	
 
 Iterator<Rate> ir=hashset.iterator();
 while (ir.hasNext()) {
	 Rate rate=ir.next();
	 User user_in=rate.user;
	 String title_in=rate.title;
	 int rating_in=rate.rating;
	 if ((user_in==user) && (title_in.equals(title)) && (rating_in != rating)) {
		 hashset.remove(rate);
		 break;
	 }
 }
 Rate new_rate=new Rate(user, title, rating);
 hashset.add(new_rate);
            return true;
    }

    public int getUserRating(User user, String title) {
        // TODO sub-problem 3
       if (user==null ||findMovie(title)==null||title==null) {
    	   return -1;
       }
       Iterator<Rate> ir=hashset.iterator();
       while (ir.hasNext()) {
      	 Rate rate=ir.next();
      	 User user_in=rate.user;
      	 String title_in=rate.title;
      	 int rating_in=rate.rating;
      	 if ((user_in==user) && (title_in.equals(title))) {
      		 return rating_in;
      	 }
       }
       return 0;
    }
    HashMap<User, List<Movie>> user_movie=new HashMap<User, List<Movie>>();
    public List<Movie> findUserMoviesWithTags(User user, String[] tags) {
        // TODO sub-problem 4'
    	if (user==null || findUser(user.getUsername())==null) {
    		return new LinkedList<Movie>();
    	}
       
      
      
    if (user_movie.keySet().contains(user)) {
    	user_movie.get(user).addAll(findMoviesWithTags(tags));
    }
    else {
    	user_movie.put(user, findMoviesWithTags(tags));
    }
    return findMoviesWithTags(tags);
    }
    boolean sorting=false;
    public List<Movie> recommend(User user) {
        // TODO sub-problem 4\
    	if (user==null || findUser(user.getUsername())==null) {
    		return new LinkedList<Movie>();
    	}
    	List<Movie> possible_list= user_movie.get(user);
    	int total;
    	int totalnumber;
    	ArrayList<Double> average=new ArrayList<Double>();
    	HashMap<Movie, Double> mv_av=new HashMap<Movie, Double>();
    	for(Movie mov : possible_list) {
    		total=0;
    		totalnumber=userlist.size();
    	for (User user_: userlist) {
    		if (getUserRating(user_,mov.getTitle())<=0) {
    			totalnumber--;
    		}
    		total+=getUserRating(user_,mov.getTitle() );
    	}
    	double final_value=0;
    	if (totalnumber!=0) {
    	average.add(total*1.0/totalnumber);
    	final_value=total*1.0/totalnumber;}
    	else {
    		average.add(0.0);
    		final_value=0.0;
    	}
    	mv_av.put(mov,final_value );
    	}
    	Collections.sort(average);
    	Collections.reverse(average);
    	List<Movie> list=new ArrayList();
    	list.addAll(mv_av.keySet());
    	
    	Comparator<Movie> comp=new Comparator<Movie>() {

			@Override
			public int compare(Movie o1, Movie o2) {
				if (mv_av.get(o1)>mv_av.get(o2)) {
					return -1;
				}
				else if (mv_av.get(o1)<mv_av.get(o2)) {
					return 1;
				}
				else {
					sorting=true;
					return 0;
				}
			}
    		
    	};
    	Collections.sort(list, comp);

    	
    	
    	if (sorting) {
    		Collections.sort(possible_list);
    		if (possible_list.size()>3) {
    			ArrayList <Movie> llist=new ArrayList<Movie>();
    			llist.add(possible_list.get(0));
    			llist.add(possible_list.get(1));
    			llist.add(possible_list.get(2));
    			return llist;
    		}
    		
    			return possible_list;
    		
    	}
    	else {
    	if (list.size()>3) {
    		ArrayList <Movie> llist=new ArrayList<Movie>();
			llist.add(list.get(0));
			llist.add(list.get(1));
			llist.add(list.get(2));
			return llist;
    	}
    	}
        return list;
    }
}

class Rate{
	User user;
	String title;
	int rating;
	Rate(User user, String title, int rating){
		this.user=user;
		this.title=title;
		this.rating=rating;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rating;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rate other = (Rate) obj;
		if (rating != other.rating)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
}