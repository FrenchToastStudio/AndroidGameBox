package dev.cutie.gamebox.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dev.cutie.gamebox.R;
import dev.cutie.gamebox.view.dto.PlayerDTO;

import java.util.ArrayList;
import java.util.List;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.ViewHolder>{
    private LayoutInflater layoutInflater;
    private List<PlayerDTO> players;
    public PlayerListAdapter(Context context){
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PlayerListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = layoutInflater.inflate(R.layout.item_player,parent,false);
        return new PlayerListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerListAdapter.ViewHolder holder, int position) {
        String nom = players.get(position).getName();
        holder.playerName.setText(nom);

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        if (players == null)
            return 0;
        return players.size();
    }

    public void addUser(String name)
    {
        if(players == null)
        {
            this.players = new ArrayList<>();
        }
        this.players.add(new PlayerDTO(name));
        notifyItemInserted(players.size()-1);
    }

    public void setUsers(List<String> names)
    {
        int oldSize = 0;
        if(this.players != null) {
            oldSize = this.players.size();
        }
        this.players = new ArrayList<>();
        for (int i=0; i < names.size(); i++)
        {
            this.players.add(new PlayerDTO(names.get(i)));
            if(oldSize -1 < i) {
                notifyItemInserted(i);
            }
            else {
                notifyItemChanged(i);
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView playerName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.playerName = itemView.findViewById(R.id.player_name);
        }

        @Override
        public void onClick(View v) {

        }
    }


}
