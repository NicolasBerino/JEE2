package monpkg.services;

import javax.ejb.Remote;

@Remote
public interface StorageInterface {

	public  String get(String key);

	public  void set(String key, String value);
	
}
