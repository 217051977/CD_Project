package maven.example.cdproject.db.FileManager;

public class FileBase {
	
	private String _id;
	private String _rev = null;
	
	public FileBase() {}
	
	public String get_id() {
		return _id;
	}
	
	public String get_rev() {
		return _rev;
	}
	
	protected void nextID() {
		_id = FileItemIDGenerator.getNextId().toString();
	}
	
	protected void startIDCounter() {
		_id = FileItemIDGenerator.startId().toString();
	}
	
}
