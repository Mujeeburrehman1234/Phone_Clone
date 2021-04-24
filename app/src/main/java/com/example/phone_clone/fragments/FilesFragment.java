package com.example.phone_clone.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phone_clone.R;
import com.example.phone_clone.adapter.FilesAdapter;
import com.example.phone_clone.adapter.MusicAdapter;
import com.example.phone_clone.adapter.VideoAdapter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilesFragment extends Fragment {
    RecyclerView mRecyclerView;
    List<String> m_item;
    List<String> m_path;
    List<String> m_files;
    List<String> m_filesPath;
    String m_curDir;
    FilesAdapter mFilesAdapter;
    View mView;
    Boolean m_isRoot = true;
    private String m_root = Environment.getExternalStorageDirectory().getPath();

    public FilesFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.file_fragment, container, false);
        mRecyclerView = mView.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getDirFromRoot(m_root);
        return mView;

    }

    public void getDirFromRoot(String p_rootPath) {
        m_item = new ArrayList<String>();

        m_path = new ArrayList<String>();
        m_files = new ArrayList<String>();
        m_filesPath = new ArrayList<String>();
        File m_file = new File(p_rootPath);
        File[] m_filesArray = m_file.listFiles();
        if (!p_rootPath.equals(m_root)) {
            m_item.add("../");
            m_path.add(m_file.getParent());
            m_isRoot = false;
        }
        m_curDir = p_rootPath;
        //sorting file list in alphabetical order
        Arrays.sort(m_filesArray);
        for (int i = 0; i < m_filesArray.length; i++) {
            File file = m_filesArray[i];
            if (file.isDirectory()) {
                m_item.add(file.getName());
                m_path.add(file.getPath());
            } else {
                m_files.add(file.getName());
                m_filesPath.add(file.getPath());
            }
        }
        for (String m_AddFile : m_files) {
            m_item.add(m_AddFile);
        }
        for (String m_AddPath : m_filesPath) {
            m_path.add(m_AddPath);
        }
        mFilesAdapter = new FilesAdapter(getContext(), m_item, m_path, m_isRoot);
        mRecyclerView.setAdapter(mFilesAdapter);
//        m_RootList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                File m_isFile=new File(m_path.get(position));
//                if(m_isFile.isDirectory())
//                {
//                    getDirFromRoot(m_isFile.toString());
//                }
//                else
//                {
//                    Toast.makeText(getContext(), "This is File", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }
//    private int setFileImageType(File m_file)
//    {
//        int m_lastIndex=m_file.getAbsolutePath().lastIndexOf(".");
//        String m_filepath=m_file.getAbsolutePath();
//        if (m_file.isDirectory())
//            return R.mipmap.folder;
//        else
//        {
//            try {
//                if(m_filepath.substring(m_lastIndex).equalsIgnoreCase(".png"))
//                {
//                    return R.drawable.ic_photo_blue_24dp;
//                }
//                else if(m_filepath.substring(m_lastIndex).equalsIgnoreCase(".jpg"))
//                {
//                    return R.drawable.ic_photo_blue_24dp;
//                }
//                else
//                {
//                    return R.drawable.ic_launcher_foreground;
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                return R.mipmap.folder;
//            }
//        }
//    }

//    String getLastDate(int p_pos)
//    {
//        File m_file=new File(m_path.get(p_pos));
//        SimpleDateFormat m_dateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        return m_dateFormat.format(m_file.lastModified());
//    }
}
