package models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;
@Table("user")
public class User extends Model{
	public String getId(){
		return this.getString("id");
	}
	public String getName(){
		return this.getString("username");
	}
	public String getPWHash(){
		return this.getString("passwordhash");
	}
	public String getGameData(){
		return this.getString("gamedata");
	}
}
