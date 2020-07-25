package edu.cnm.deepdive.playnumbers.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.Transformations;
import edu.cnm.deepdive.playnumbers.model.entity.Activity;
import edu.cnm.deepdive.playnumbers.model.entity.Activity.Type;
import edu.cnm.deepdive.playnumbers.model.entity.User;
import edu.cnm.deepdive.playnumbers.model.pojo.ActivityWithProgress;
import edu.cnm.deepdive.playnumbers.service.ActivityRepository;
import edu.cnm.deepdive.playnumbers.service.UserRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

  private final UserRepository userRepository;
  private final ActivityRepository activityRepository;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;
  private final MutableLiveData<ActivityWithProgress> activity;
  private final MutableLiveData<Type> type;
  private final LiveData<List<ActivityWithProgress>> activitiesForType;


  public MainViewModel(@NonNull Application application) {
    super(application);
    userRepository = new UserRepository(application);
    activityRepository = new ActivityRepository(application);
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    activity = new MutableLiveData<>();
    type = new MutableLiveData<>();
    activitiesForType = Transformations.switchMap(type, (t) -> activityRepository.get(t));
  }

  public void setType(Type type){
    this.type.setValue(type);
  }

  public LiveData<List<ActivityWithProgress>> getActivitiesForType() {
    return activitiesForType;
  }

  public LiveData<List<User>> getUser() {
    return userRepository.getAll();
  }

  public LiveData<List<ActivityWithProgress>> getActivity() {
    return activityRepository.getAll();
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public void setActivityId(long id) {
    throwable.setValue(null);
    pending.add(
        activityRepository.get(id)
        .subscribe(
            this.activity::postValue,
            this.throwable::postValue
        )

    );
  }

   public void saveActivity(ActivityWithProgress activity) {
     throwable.setValue(null);
     pending.add(
         activityRepository.save(activity)
         .subscribe(
             () -> {},
             this.throwable::postValue
         )
     );
   }
  public void deleteActivity(Activity activity) {
    throwable.setValue(null);
    pending.add(
        activityRepository.delete(activity)
            .subscribe(
                () -> {},
                this.throwable::postValue
            )
    );
  }

  @OnLifecycleEvent(Event.ON_STOP)    //@ tells whatever task is in process trow it away
  private void clearPending() {
     pending.clear();
  }
}

