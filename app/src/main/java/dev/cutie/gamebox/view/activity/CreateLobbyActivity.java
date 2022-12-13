package dev.cutie.gamebox.view.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import dev.cutie.gamebox.R;

public class CreateLobbyActivity extends Fragment
{
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState)
    {

        return inflater.inflate(R.layout.create_lobby, container, false);
    }
}
