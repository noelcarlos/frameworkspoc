package es.generali.strutspoc.support;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.sync.RedisCommands;

public class RedistPersistenceDataStore implements IPersistenceDataStore {
	RedisClient client;
	StatefulRedisConnection<String, String> connection; 
	
	public RedistPersistenceDataStore() {
		client = RedisClient.create("redis://localhost:6379/0");
		connection = client.connect();
	}
	
	public Object serializeFromString( String s ) throws IOException ,
	 		ClassNotFoundException {
		byte [] data = Base64.getDecoder().decode( s );
		ObjectInputStream ois = new ObjectInputStream( 
		new ByteArrayInputStream(  data ) );
		Object o  = ois.readObject();
		ois.close();
		return o;
	}
		
	/** Write the object to a Base64 string. */
	public String serializeToString( Serializable o ) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream( baos );
		oos.writeObject( o );
		oos.close();
		return Base64.getEncoder().encodeToString(baos.toByteArray()); 
	}
	

	@Override
	public Object getAttribute(String id, String name) {
		RedisCommands<String, String> syncCommands = connection.sync();
		String res = syncCommands.get(id+":"+name);
		if (res == null) {
			return null;
		}
		try {
			return serializeFromString(res);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void setAttribute(String id, String name, Object value) {
		RedisCommands<String, String> syncCommands = connection.sync();
		try {
			syncCommands.set(id+":"+name, serializeToString((Serializable)value));
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void removeAttribute(String id, String name) {
		RedisCommands<String, String> syncCommands = connection.sync();
		try {
			syncCommands.del(id+":"+name);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
