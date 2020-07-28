package edu.cnm.deepdive.playnumbers.model.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

/**
 * Provides the ability to store Activity objects in the database and retrieve them from it.
 */
@Entity(
    indices = {
        @Index(value = "name"),
        @Index(value = "level")
    }
)
public class Activity {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "activity_id")
  private long id;

  @NonNull
  private String name;

  @NonNull
  @ColumnInfo(name = "class_name")
  private String className;

  private int level;

  @NonNull
  @ColumnInfo(index = true)
  private Type type;

  /**
   * Returns the activity's id.
   *
   * @return a long containing the value for Id.
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the activity's id.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Returns the activity's name.
   *
   * @return a String representing the activity's name.
   */
  @NonNull
  public String getName() {
    return name;
  }

  /**
   * Sets the activity's name.
   */
  public void setName(@NonNull String name) {
    this.name = name;
  }

  /**
   * Returns the activity's level.
   *
   * @return an int containing the activity's level.
   */
  public int getLevel() {
    return level;
  }

  /**
   * Sets the activity's level.
   */
  public void setLevel(int level) {
    this.level = level;
  }

  /**
   * Returns the activity's class name.
   *
   * @return a String representing the activity's class name.
   */
  @NonNull
  public String getClassName() {
    return className;
  }

  /**
   * Sets the activity's class name.
   */
  public void setClassName(@NonNull String className) {
    this.className = className;
  }

  /**
   * Returns the activity's type.
   *
   * @return the activity type.
   */
  @NonNull
  public Type getType() {
    return type;
  }

  /**
   * Sets the activity's type.
   */
  public void setType(@NonNull Type type) {
    this.type = type;
  }

  /**
   * Represents a fixed set of constants for the activities' types.
   */
  public enum Type {
    MATCHING, MISSING;

    /**
     * Returns the conversion between classes Type and Integer for an easier mapping of their
     * instances.
     *
     * @param value represents the Type's input.
     * @return an Integer value for Type
     */
    @TypeConverter                                     //tell integer to store the Type
    public static Integer typeToInteger(Type value) {
      return (value != null) ? value.ordinal() : null;
    }

    /**
     * Returns the conversion between classes Integer and Type for an easier mapping of their
     * instances.
     *
     * @param value represents the Integer's input.
     * @return the Type value for an Integer.
     */
    @TypeConverter
    public static Type integerToType(Integer value) {
      return (value != null) ? Type.values()[value] : null;
    }
  }
}
