
import java.util.HashMap;
import java.util.Map;

public class Movie implements Comparable<Movie>{
    private String title;
    private String[] tags;
    public Movie(String title) { this.title = title;}
    
    public Movie(String title, String[] tags) {
    	this.title=title;
    	this.tags=tags;
    }
    @Override
    public String toString() {
        return title;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	@Override
	public int compareTo(Movie arg0) {
		// TODO Auto-generated method stub
		
		return this.title.compareTo(arg0.title);
	}
    
    
}
