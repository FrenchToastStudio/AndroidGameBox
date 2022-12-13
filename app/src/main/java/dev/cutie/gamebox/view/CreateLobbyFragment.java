package dev.cutie.gamebox.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dev.cutie.gamebox.R;
import dev.cutie.gamebox.view.adapter.PlayerListAdapter;
import dev.cutie.gamebox.view.interace.ICreateLobbyView;

import java.util.List;

public class CreateLobbyFragment extends Fragment implements ICreateLobbyView
{

    private PlayerListAdapter adapter;
    private RecyclerView rvPlayer;
    private List<String> players;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_lobby, parent, false);
        this.adapter = new PlayerListAdapter(this.getContext());
        this.rvPlayer = view.findViewById(R.id.player_rv);
        this.rvPlayer.setAdapter(this.adapter);
        this.rvPlayer.setLayoutManager(new LinearLayoutManager(this.getContext()));
        if(players != null) {
            setUsers(players);
        }
        // Defines the xml file for the fragment

        return view;
    }

    private void runOnUiThread(Runnable runnable) {
    }

    @Override
    public void setUsers(List<String> strings)
    {
        if(getActivity() == null)
        {
            this.players = strings;
            return;
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(adapter != null) {
                    adapter.setUsers(strings);
                }
            }
        });
    }

    public void removeUser()
    {

    }

}
