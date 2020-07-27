package edu.cnm.deepdive.playnumbers.model.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * The class provides the ability to store User objects in the database and retrieve them.
 */
@Entity(
    indices = @Index(value = {"name", "oauth_key"}, unique = true)
)
public class User {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "user_id")
  private long id;

  @NonNull
  @ColumnInfo(name = "name", collate = ColumnInfo.NOCASE)
  private String name;

  @NonNull
  @ColumnInfo(name = "age")
  private int age;


  @ColumnInfo(name = "oauth_key", collate = ColumnInfo.NOCASE)
  private String oauthKey;

  /**
   * Method used to get the user's id.
   *
   * @return a long containing the value for Id.
   */
  public long getId() {
    return id;
  }

  /**
   * Method used to set the user's id.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Method used to get the user's name.
   *
   * @return a String representing the user's name.
   */
  @NonNull
  public String getName() {
    return name;
  }

  /**
   * Method used to set the user's name.
   */
  public void setName(@NonNull String name) {
    this.name = name;
  }

  /**
   * Method used to get the user's age.
   *
   * @return an int containing the user's age.
   */
  public int getAge() {
    return age;
  }

  /**
   * Method used to set the user's age.
   */
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * Method used to get the user's Oauth Key.
   *
   * @return a String representing the user's Oauth Key.
   */
  public String getOauthKey() {
    return oauthKey;
  }

  /**
   * Method used to set the user's Oauth Key.
   */
  public void setOauthKey(String oauthKey) {
    this.oauthKey = oauthKey;
  }
}

