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

public class UserRepository {

  private final Context context;
  private final PlayNumbersDatabase database;
  private final UserDao userDao;


  public UserRepository(Context context) {
    this.context = context;
    database = PlayNumbersDatabase.getInstance();
    userDao = database.getUserDao();
  }

  public LiveData<List<User>> getAll() {
    return userDao.selectAll();

  }
  public Single<UserWithProgress> get(long id) {
    return userDao.selectedById(id)
        .subscribeOn(Schedulers.io());
  }

  public Completable save(User user) {
    if (user.getId() == 0) {
      return Completable.fromSingle(userDao.insert(user))
          .subscribeOn(Schedulers.io());

    } else {
      return Completable.fromSingle(userDao.update(user))
          .subscribeOn(Schedulers.io());

    }
  }
  public Completable delete(User user) {
    if (user.getId() == 0) {
      return Completable.fromAction(() -> {})
          .subscribeOn(Schedulers.io());
    } else {
      return Completable.fromSingle(userDao.delete(user))
          .subscribeOn(Schedulers.io());
    }
  }

}
