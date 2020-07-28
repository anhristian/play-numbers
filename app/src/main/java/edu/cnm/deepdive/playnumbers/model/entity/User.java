package edu.cnm.deepdive.playnumbers.model.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Implements the ability to store User objects in the database and retrieve them.
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
   * Returns the user's id.
   *
   * @return a long containing the value for Id.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the user's id.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Returns the user's name.
   *
   * @return a String representing the user's name.
   */
  @NonNull
  public String getName() {
    return name;
  }

  /**
   * Sets the user's name.
   */
  public void setName(@NonNull String name) {
    this.name = name;
  }

  /**
   * Returns the user's age.
   *
   * @return an int containing the user's age.
   */
  public int getAge() {
    return age;
  }

  /**
   * Sets the user's age.
   */
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * Returns the user's Oauth Key.
   *
   * @return a String representing the user's Oauth Key.
   */
  public String getOauthKey() {
    return oauthKey;
  }

  /**
   * Sets the user's Oauth Key.
   */
  public void setOauthKey(String oauthKey) {
    this.oauthKey = oauthKey;
  }
}

