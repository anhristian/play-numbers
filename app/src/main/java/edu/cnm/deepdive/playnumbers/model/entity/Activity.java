package edu.cnm.deepdive.playnumbers.model.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

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
  //@ColumnInfo(index = true)
  private String name;

  @NonNull
  @ColumnInfo(name = "class_name")
  private String className;  //type activity

  private int level;

  @NonNull
  @ColumnInfo(index = true)
  private Type type;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @NonNull
  public String getName() {
    return name;
  }

  public void setName(@NonNull String name) {
    this.name = name;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  @NonNull
  public String getClassName() {
    return className;
  }

  public void setClassName(@NonNull String className) {
    this.className = className;
  }

  @NonNull
  public Type getType() {
    return type;
  }

  public void setType(@NonNull Type type) {
    this.type = type;
  }

  public enum Type {
    MATCHING, MISSING;

    @TypeConverter                                     //tell integer to store the Type
    public static Integer typeToInteger(Type value) {
      return (value != null) ? value.ordinal() : null;
    }
    @TypeConverter
    public static Type integerToType( Integer value) {
      return (value != null) ? Type.values()[value] : null;
    }
  }
}
