package edu.cnm.deepdive.playnumbers.service;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;


/**
 * Implements the service of Google Sign In.
 */
public class GoogleSignInService {


  private static Application context;

  private final GoogleSignInClient client;
  private final MutableLiveData<GoogleSignInAccount> account;
  private final MutableLiveData<Throwable> throwable;

  private GoogleSignInService() {   //the constructor
    account = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    GoogleSignInOptions options = new GoogleSignInOptions.Builder()
        .requestEmail()  //invoke this request that return an builder object whit which invoke the next.
        .requestId()
        .requestProfile()
        .build();
    client = GoogleSignIn.getClient(context, options);
  }

  /**
   * Sets a context for the service that came from the instance.
   *
   * @param context sets the sign in.
   */
  public static void setContext(Application context) {
    GoogleSignInService.context = context;
  }

  /**
   * Returns the instance of GoogleSingInService.
   *
   * @return GoogleSignInService instance.
   */
  public static GoogleSignInService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * Obtains a LiveData to getAccount property.
   *
   * @return a LiveData for the account .
   */
  public MutableLiveData<GoogleSignInAccount> getAccount() {
    return account;
  }

  /**
   * Obtains a LiveData to throwable property.
   *
   * @return a LiveData for the throwable.
   */
  public MutableLiveData<Throwable> getThrowable() {
    return throwable;
  }

  /**
   * Obtains the Task for refresh property.
   *
   * @return an updated GoogleSignInAccount.
   */
  public Task<GoogleSignInAccount> refresh() {
    return client.silentSignIn()
        .addOnSuccessListener(this::update)  //lambda method reference here
        .addOnFailureListener(this::update);
  }

  /**
   * Sets the starting Sign In process .
   */
  public void startSignIn(Activity activity, int requestCode) {
    update((GoogleSignInAccount) null); //casting to show specific what GSIA
    Intent intent = client.getSignInIntent();
    activity.startActivityForResult(intent, requestCode);
  }

  /**
   * Obtains the Task for completing the Sign In.
   *
   * @return the completed Sign in task.
   */
  public Task<GoogleSignInAccount> completeSignIn(Intent data) {
    Task<GoogleSignInAccount> task = null;
    try {
      task = GoogleSignIn.getSignedInAccountFromIntent(data);
      update(task.getResult(ApiException.class));
    } catch (ApiException e) {
      update(e);
    }
    return task;
  }

  /**
   * Obtains the Task for SignOut property.
   *
   * @return a task of SignOUt seated to null.
   */
  public Task<Void> signOut() {
    return client.signOut()
        .addOnCompleteListener((ignored) -> update((GoogleSignInAccount) null));
  }

  private void update(GoogleSignInAccount account) {
    this.account.setValue(account);
    this.throwable.setValue(null);
  }

  private void update(Throwable throwable) {
    this.account.setValue(null);
    this.throwable.setValue(throwable);

  }

  private static class InstanceHolder {

    private static final GoogleSignInService INSTANCE = new GoogleSignInService(); //the class is load in the memory when is a request for getInstance and keep the result for next requests. Only happens once.
  }
}
