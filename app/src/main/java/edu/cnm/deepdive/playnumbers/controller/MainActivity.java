package edu.cnm.deepdive.playnumbers.controller;

import static edu.cnm.deepdive.playnumbers.model.entity.Activity.Type.MATCHING;
import static edu.cnm.deepdive.playnumbers.model.entity.Activity.Type.MISSING;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import edu.cnm.deepdive.playnumbers.R;
import edu.cnm.deepdive.playnumbers.service.GoogleSignInService;
import edu.cnm.deepdive.playnumbers.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity
    implements BottomNavigationView.OnNavigationItemSelectedListener {

  private GoogleSignInService signInService;
  private NavController navController;

 /* private Button buttonActivityOne;*/


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setupNavigation();
    setupObservers();

  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    getMenuInflater().inflate(R.menu.options, menu);
    return true;
  }
  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    boolean handled = true;
    //noinspection SwitchStatementWithTooFewBranches
    switch (item.getItemId()) {
      case R.id.sign_out:
        signInService.signOut().addOnCompleteListener((ignored) -> switchToLogin());
        break;
      default:
        handled = super.onOptionsItemSelected(item);
    }
    return handled;
  }
  private void setupNavigation() {
    BottomNavigationView navView = findViewById(R.id.nav_view);
    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
        R.id.navigation_home, R.id.navigation_activities, R.id.navigation_matching, R.id.navigation_missing)
        .build();
    navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    // NavigationUI.setupWithNavController(navView, navController);
    navView.setOnNavigationItemSelectedListener(this);
  }
  private void setupObservers() {
    MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    getLifecycle().addObserver(viewModel);
    //Toast - view widget at the bottom of the screen that appears and disappears.
    viewModel.getThrowable().observe(this, (throwable) -> {
      if (throwable != null) {
        //has a static factory method
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG).show();
      }
    });
    signInService = GoogleSignInService.getInstance();
  }
  private void switchToLogin() {
    Intent intent = new Intent(this, LoginActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    boolean handled  = true;
    Bundle args = new Bundle();
    switch (item.getItemId()) {
      case R.id.navigation_matching:
        args.putSerializable(LearningActivitiesFragment.TYPE_KEY, MATCHING);
        navController.navigate(R.id.navigation_activities, args);
        break;
      case R.id.navigation_missing:
        args.putSerializable(LearningActivitiesFragment.TYPE_KEY, MISSING);
        navController.navigate(R.id.navigation_activities, args);
        break;
      case R.id.navigation_home:
        navController.navigate(R.id.navigation_home);
        break;
      default:
        handled = false;
    }
    return handled;
  }
}
