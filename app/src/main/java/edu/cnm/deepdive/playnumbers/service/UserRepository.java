package edu.cnm.deepdive.playnumbers.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.playnumbers.model.dao.UserDao;
import edu.cnm.deepdive.playnumbers.model.entity.User;
import edu.cnm.deepdive.playnumbers.model.pojo.UserWithProgress;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * The class holds methods get, save, and delete executed on a user of application.
 */
public class UserRepository {

  private final Context context;
  private final PlayNumbersDatabase database;
  private final UserDao userDao;

  /**
   * The constructor set the context by getting an instance from database.
   *
   * @param context user context
   */
  public UserRepository(Context context) {
    this.context = context;
    database = PlayNumbersDatabase.getInstance();
    userDao = database.getUserDao();
  }

  /**
   * The method gets a list of all users.
   *
   * @return users list.
   */
  public LiveData<List<User>> getAll() {
    return userDao.selectAll();
  }

  /**
   * The method gets a single user by its id.
   *
   * @param id by which a user is selected
   * @return user's id.
   */
  public Single<UserWithProgress> get(long id) {
    return userDao.selectedById(id)
        .subscribeOn(Schedulers.io());
  }

  /**
   * The method save a new user or update an existing one.
   *
   * @param user which is going to be saved or updated.
   * @return inserted or updated user.
   */
  public Completable save(User user) {
    if (user.getId() == 0) {
      return Completable.fromSingle(userDao.insert(user))
          .subscribeOn(Schedulers.io());

    } else {
      return Completable.fromSingle(userDao.update(user))
          .subscribeOn(Schedulers.io());

    }
  }

  /**
   * The method delete an existing user.
   *
   * @param user which is going to be removed.
   * @return no user.
   */
  public Completable delete(User user) {
    if (user.getId() == 0) {
      return Completable.fromAction(() -> {
      })
          .subscribeOn(Schedulers.io());
    } else {
      return Completable.fromSingle(userDao.delete(user))
          .subscribeOn(Schedulers.io());
    }
  }

}
