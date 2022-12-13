package dev.cutie.gamebox;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import dev.cutie.androidtoolbox.bootstrap.AppBootStrap;
import dev.cutie.androidtoolbox.commands.CommandManager;
import dev.cutie.androidtoolbox.store.StoreContext;
import dev.cutie.androidtoolbox.store.facade.IStoreFacade;

import android.view.Menu;
import android.view.MenuItem;
import dev.cutie.gamebox.commands.AddPlayerCommand;
import dev.cutie.gamebox.commands.executable.IAddPlayerExecutable;
import dev.cutie.gamebox.controller.CreateLobbyController;
import dev.cutie.gamebox.store.AppStore;
import dev.cutie.gamebox.store.GameBoxStoreContext;
import dev.cutie.gamebox.store.StoreAppState;
import dev.cutie.gamebox.view.CreateLobbyFragment;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private AddPlayerCommand addPlayerCommand;
    AppStore store;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = findViewById(R.id.test_button);

        this.addPlayerCommand = new AddPlayerCommand();

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addPlayerCommand.toggle();
            }
        });
        appSetUp();

        launch();
        addCommandToManager();
    }

    private void appSetUp()
    {
        AppBootStrap instance = AppBootStrap.getInstance();
        GameBoxStoreContext storeContext = new GameBoxStoreContext();
        AppStore appStore = new AppStore(storeContext);
        CommandManager.launch();

        instance.addService(IStoreFacade.class, appStore);
    }


    public void addCommandToManager()
    {
        CommandManager.getInstance().attachCommand(this, addPlayerCommand );
    }
    private void launch()
    {
        CreateLobbyFragment lobbyFragment = new CreateLobbyFragment();
        CreateLobbyController lobbyController = new CreateLobbyController(lobbyFragment);
        subscribeTocommand(lobbyController);
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_view, lobbyFragment, null)
                .commit();
    }

    public void subscribeTocommand(IAddPlayerExecutable executable)
    {
        addPlayerCommand.setExecutable(executable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}