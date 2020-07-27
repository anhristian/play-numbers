package edu.cnm.deepdive.playnumbers.model.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

/**
 * The class provides the ability to store Activity objects in the database and retrieve them from it.
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
   *Method used to get the activity's id.
   * @return a long containing the value for Id.
   */
  public long getId() {
    return id;
  }

  /**
   *Method used to set the activity's id.
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   *Method used to get the activity's name.
   * @return a String representing the activity's name.
   */
  @NonNull
  public String getName() {
    return name;
  }

  /**
   *Method used to set the activity's name.
   */
  public void setName(@NonNull String name) {
    this.name = name;
  }

  /**
   *Method used to get the activity's level.
   * @return an int containing the activity's level.
   */
  public int getLevel() {
    return level;
  }

  /**
   *Method used to set the activity's level.
   */
  public void setLevel(int level) {
    this.level = level;
  }

  /**
   *Method used to get the activity's class name.
   * @return a String representing the activity's class name.
   */
  @NonNull
  public String getClassName() {
    return className;
  }

  /**
   *Method used to set the activity's class name.
   */
  public void setClassName(@NonNull String className) {
    this.className = className;
  }

  /**
   *Method used to get the activity's type.
   * @return the activity type.
   */
  @NonNull
  public Type getType() {
    return type;
  }

  /**
   *Method used to set the activity's type.
   */
  public void setType(@NonNull Type type) {
    this.type = type;
  }

  /**
   * The class represents a fixed set of constants for the activities' types.
   */
  public enum Type {
    MATCHING, MISSING;

    /**
     * The method provides conversion between classes Type and Integer for an easier mapping of their instances.
     * @param value represents the Type's input.
     * @return an Integer value for Type
     */
    @TypeConverter                                     //tell integer to store the Type
    public static Integer typeToInteger(Type value) {
      return (value != null) ? value.ordinal() : null;
    }

    /**
     * The method provides conversion between classes Integer and Type for an easier mapping of their instances.
     * @param value represents the Integer's input.
     * @return the Type value for an Integer.
     */
    @TypeConverter
    public static Type integerToType( Integer value) {
      return (value != null) ? Type.values()[value] : null;
    }
  }
}
