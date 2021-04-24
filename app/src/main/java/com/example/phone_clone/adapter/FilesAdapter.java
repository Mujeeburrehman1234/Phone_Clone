package com.example.phone_clone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phone_clone.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FilesAdapter extends RecyclerView.Adapter<FilesAdapter.ViewHolder>{

    private List<String> m_item=new ArrayList<>();
    private List<String> m_path=new ArrayList<>();
    public ArrayList<Integer> m_selectedItem;
    Context m_context;
    Boolean m_isRoot;

    public FilesAdapter(Context context, List<String> m_item, List<String> m_path, Boolean m_isRoot) {
        m_context=context;
        this.m_item=m_item;
        this.m_path=m_path;
       // m_selectedItem=new ArrayList<Integer>();
        this.m_isRoot=m_isRoot;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext ()).
                inflate ( R.layout.files_layout,parent,false);
       FilesAdapter.ViewHolder viewHolder = new FilesAdapter.ViewHolder ( view );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      try{
          holder.fileTile.setText(m_item.get(position));
          holder.fileImage.setImageResource(setFileImageType(new File(m_path.get(position))));
      }catch (Exception e)
      {
          e.getCause();
      }

       // holder.fileDate.setText(getLastDate(position));

    }

    @Override
    public int getItemCount() {
        return m_item.size ()+m_path.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fileImage;
        TextView fileTile,fileDate;
        CheckBox fileCheckbox;
    public ViewHolder(@NonNull View itemView) {
        super ( itemView );
        fileImage = itemView.findViewById ( R.id.files_thumb );
        fileTile = itemView.findViewById ( R.id.files_title );
        fileDate = itemView.findViewById ( R.id.videos_size );
        fileCheckbox = itemView.findViewById ( R.id.files_checkbox);


    }
}
    private int setFileImageType(File m_file)
    {
        int m_lastIndex=m_file.getAbsolutePath().lastIndexOf(".");
        String m_filepath=m_file.getAbsolutePath();
        if (m_file.isDirectory())
            return R.mipmap.folder;
        else
        {
            try {
                if(m_filepath.substring(m_lastIndex).equalsIgnoreCase(".png"))
                {
                    return R.drawable.ic_photo_blue_24dp;
                }
                else if(m_filepath.substring(m_lastIndex).equalsIgnoreCase(".jpg"))
                {
                    return R.drawable.ic_photo_blue_24dp;
                }
                else
                {
                    return R.drawable.ic_launcher_foreground;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return R.mipmap.folder;
            }
        }
    }
    String getLastDate(int p_pos)
    {
        File m_file=new File(m_path.get(p_pos));
        SimpleDateFormat m_dateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return m_dateFormat.format(m_file.lastModified());
    }
}
